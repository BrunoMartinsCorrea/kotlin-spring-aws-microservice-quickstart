package com.company.tribe.utils

import com.company.tribe.account.Account
import com.company.tribe.account.AccountEntity

fun Account.toEntity() =
    AccountEntity(
        id = this.id,
        username = this.username,
        password = this.password
    )

fun AccountEntity.toPlainObject() = Account(
    id = this.id,
    username = this.username,
    password = this.password
)
