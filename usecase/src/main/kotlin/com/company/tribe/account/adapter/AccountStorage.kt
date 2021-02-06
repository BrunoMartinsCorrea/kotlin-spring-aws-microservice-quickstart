package com.company.tribe.account.adapter

import com.company.tribe.account.Account

interface AccountStorage {
    fun save(account: Account)
    fun findById(id: Long): Account
    fun delete(id: Long)
}
