package com.company.tribe.security.config

import com.company.tribe.security.service.UserDetailsServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy.STATELESS
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.filter.OncePerRequestFilter

@EnableWebSecurity
class SecurityConfig(
    val userDetailsService: UserDetailsServiceImpl,
    val tokenRequestFilter: OncePerRequestFilter
) : WebSecurityConfigurerAdapter() {
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.userDetailsService(userDetailsService)
    }

    override fun configure(http: HttpSecurity?) {
        http!!.csrf().disable()
            .authorizeRequests().antMatchers(*permitAll).permitAll()
            .anyRequest().authenticated()
            .and().sessionManagement().sessionCreationPolicy(STATELESS)
            .and().headers().frameOptions().sameOrigin()
        http.addFilterBefore(tokenRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    override fun authenticationManager(): AuthenticationManager {
        return super.authenticationManager()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    companion object {
        private val swaggerEndpoints = arrayOf(
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
        )

        private val h2ConsoleEndpoints = arrayOf(
            "/h2-console/**",
            "/console/**"
        )

        private val permitAll = arrayOf(
            "/sign-in",
            "/sign-up",
            "/health",
            *swaggerEndpoints,
            *h2ConsoleEndpoints
        )
    }
}
