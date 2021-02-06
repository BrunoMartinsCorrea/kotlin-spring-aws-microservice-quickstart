package com.company.tribe.account

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.util.json.Jackson
import com.company.tribe.account.adapter.AccountStorage
import java.io.BufferedReader
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class AccountStorageImpl(
    val storage: AmazonS3,
    @Value("\${aws.s3.bucket-name}") val bucketName: String
) : AccountStorage {
    override fun save(account: Account) {
        val key = generateKey(account.id)
        val content = Jackson.toJsonString(account)

        storage.putObject(bucketName, key, content)
    }

    override fun findById(id: Long): Account {
        val key = generateKey(id)

        val s3Object = storage.getObject(bucketName, key)
        val content = BufferedReader(s3Object.objectContent.reader()).readText()

        return Jackson.fromJsonString(content, Account::class.java)
    }

    override fun delete(id: Long) {
        val key = "user-$id"

        storage.deleteObject(bucketName, key)
    }

    private fun generateKey(id: Long) = FILE_PREFIX + id

    companion object {
        private const val FILE_PREFIX = "account-"
    }
}
