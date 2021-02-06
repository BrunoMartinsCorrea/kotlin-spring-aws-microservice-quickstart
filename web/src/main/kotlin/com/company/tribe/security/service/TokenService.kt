package com.company.tribe.security.service

import io.jsonwebtoken.Claims
import java.util.Date
import org.springframework.security.core.userdetails.UserDetails

interface TokenService {
    fun generate(userDetails: UserDetails): String
    fun validate(token: String, userDetails: UserDetails): Boolean
    fun extractUsername(token: String): String
    fun extractExpiration(token: String): Date
    fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T
}
