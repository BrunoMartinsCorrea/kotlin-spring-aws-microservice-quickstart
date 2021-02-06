package com.company.tribe.security.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm.HS256
import java.util.Date
import java.util.concurrent.TimeUnit.HOURS
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class TokenServiceImpl : TokenService {
    private val expirationInMillis = HOURS.toMillis(24)
    private val secretKey = "Jd*@jC&s%cBOia!a@h"

    override fun generate(userDetails: UserDetails): String = create(mutableMapOf(), userDetails.username)

    override fun validate(token: String, userDetails: UserDetails) = extractUsername(token) == userDetails.username &&
        !isExpired(token)

    override fun extractUsername(token: String): String = extractClaim(token, Claims::getSubject)

    override fun extractExpiration(token: String): Date = extractClaim(token, Claims::getExpiration)

    override fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

    private fun create(claims: Map<String, Any>, subject: String) = Jwts.builder()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(Date(System.currentTimeMillis()))
        .setExpiration(Date(System.currentTimeMillis().plus(expirationInMillis)))
        .signWith(HS256, secretKey)
        .compact()!!

    private fun extractAllClaims(token: String): Claims =
        Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body

    private fun isExpired(token: String) = extractExpiration(token).before(Date())
}
