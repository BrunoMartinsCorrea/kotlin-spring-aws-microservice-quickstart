package com.company.tribe.util

import com.company.tribe.account.Account
import com.company.tribe.dto.AccountDto

fun Account.toDto() = AccountDto(
    id = this.id,
    username = this.username
)
