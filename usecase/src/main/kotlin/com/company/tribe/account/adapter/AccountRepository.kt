package com.company.tribe.account.adapter

import com.company.tribe.account.Account

interface AccountRepository {
    fun save(account: Account): Account
    fun findById(id: Long): Account
    fun findByUsername(username: String): Account
    fun deleteById(id: Long)
}
