package com.company.tribe.account

import com.company.tribe.account.adapter.AccountRepository
import com.company.tribe.utils.toEntity
import com.company.tribe.utils.toPlainObject
import org.springframework.stereotype.Repository

@Repository
class AccountRepositoryImpl(
    private val accountDao: AccountDao
) : AccountRepository {
    override fun save(account: Account): Account {
        return accountDao.save(account.toEntity()).toPlainObject()
    }

    override fun findById(id: Long): Account {
        val accountEntity = accountDao.findById(id)
        return accountEntity.get().toPlainObject()
    }

    override fun findByUsername(username: String): Account {
        val accountEntity = accountDao.findByUsername(username)
        return accountEntity.get().toPlainObject()
    }

    override fun deleteById(id: Long) {
        accountDao.deleteById(id)
    }
}
