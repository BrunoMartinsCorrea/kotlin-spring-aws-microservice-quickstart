package com.company.tribe.account

import java.util.Optional
import org.springframework.data.repository.CrudRepository

interface AccountDao : CrudRepository<AccountEntity, Long> {
    fun findByUsername(username: String): Optional<AccountEntity>
}
