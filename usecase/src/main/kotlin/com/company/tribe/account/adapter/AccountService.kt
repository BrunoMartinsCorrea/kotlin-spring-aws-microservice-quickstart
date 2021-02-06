package com.company.tribe.account.adapter

import com.company.tribe.account.Account

interface AccountService {
    fun save(account: Account): Account
    fun findById(id: Long): Account
    fun findByUsername(username: String): Account
    fun delete(id: Long)
}
