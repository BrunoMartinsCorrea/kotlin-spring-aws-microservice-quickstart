package com.company.tribe.controller

import com.company.tribe.account.Account
import com.company.tribe.account.adapter.AccountService
import com.company.tribe.security.service.TokenService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthenticationController(
    val authenticationManager: AuthenticationManager,
    val userDetailsService: UserDetailsService,
    val tokenService: TokenService,
    val accountService: AccountService
) {
    @Operation(summary = "Sign in in the system")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "Has signed in the system"),
            ApiResponse(responseCode = "404", description = "Did not find the account"),
            ApiResponse(responseCode = "500", description = "Something went wrong")
        ]
    )
    @PostMapping("sign-in")
    fun signIn(@RequestBody account: Account): ResponseEntity<Map<String, String>> {
        try {
            val authentication = UsernamePasswordAuthenticationToken(account.username, account.password)
            authenticationManager.authenticate(authentication)
            val userDetails = userDetailsService.loadUserByUsername(account.username)
            val token = tokenService.generate(userDetails)
            val response = mapOf("token" to token)

            return ResponseEntity.ok(response)
        } catch (ex: BadCredentialsException) {
            throw Exception("Incorrect username or password", ex)
        }
    }

    @Operation(summary = "Sign up in the system")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Has signed up in the system"),
            ApiResponse(responseCode = "500", description = "Something went wrong")
        ]
    )
    @PostMapping("sign-up")
    fun signUp(@RequestBody account: Account): ResponseEntity<Void> {
        val encryptedPassword = BCryptPasswordEncoder().encode(account.password)
        val accountEncrypted = account.copy(password = encryptedPassword)
        accountService.save(accountEncrypted)
        return ResponseEntity.ok().build()
    }
}
