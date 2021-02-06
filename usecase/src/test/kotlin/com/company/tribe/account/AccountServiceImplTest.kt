package com.company.tribe.account

import com.company.tribe.account.adapter.AccountRepository
import com.company.tribe.account.adapter.AccountStorage
import io.mockk.every
import io.mockk.spyk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Test

class AccountServiceImplTest {
    private val repository = spyk<AccountRepository>()
    private val storage = spyk<AccountStorage>()
    private val service = AccountServiceImpl(repository, storage)

    @Test
    fun `Should save`() {
        val account = Account(0, "Test", "@something!")
        val accountSaved = account.copy(id = 1)

        every { repository.save(account) } returns accountSaved

        val accountResult = service.save(account)

        verify { repository.save(account) }
        assertEquals(accountSaved, accountResult)
    }

    @Test
    fun `Should delete`() {
        val id = 1L
        service.delete(id)
        verify { repository.deleteById(id) }
    }
}
