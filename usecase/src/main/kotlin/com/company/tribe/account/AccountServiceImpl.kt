package com.company.tribe.account

import com.company.tribe.account.adapter.AccountRepository
import com.company.tribe.account.adapter.AccountService
import com.company.tribe.account.adapter.AccountStorage
import org.springframework.stereotype.Service

@Service
class AccountServiceImpl(
    private val repository: AccountRepository,
    private val storage: AccountStorage
) : AccountService {
    override fun save(account: Account): Account {
        storage.save(account)
        return repository.save(account)
    }

    override fun findById(id: Long): Account {
        return repository.findById(id)
    }

    override fun findByUsername(username: String): Account {
        return repository.findByUsername(username)
    }

    override fun delete(id: Long) {
        repository.deleteById(id)
    }
}
