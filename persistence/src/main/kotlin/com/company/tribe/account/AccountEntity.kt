package com.company.tribe.account

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id

@Entity(name = "account")
class AccountEntity(
    @Id @GeneratedValue(strategy = IDENTITY) val id: Long,
    val username: String,
    val password: String
)
