package com.company.tribe.security.service

import com.company.tribe.account.adapter.AccountService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    val accountService: AccountService
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val account = accountService.findByUsername(username!!)
        return User(account.username, account.password, emptyList())
    }
}
