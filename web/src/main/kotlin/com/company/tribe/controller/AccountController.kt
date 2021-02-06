package com.company.tribe.controller

import com.company.tribe.account.adapter.AccountService
import com.company.tribe.dto.AccountDto
import com.company.tribe.util.toDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("account")
class AccountController(
    val accountService: AccountService
) {
    @Operation(summary = "Return an account based on ID")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Returned the account"),
            ApiResponse(responseCode = "404", description = "Did not find the account"),
            ApiResponse(responseCode = "500", description = "Something went wrong")
        ]
    )
    @GetMapping("{id}")
    fun read(@PathVariable id: Long): ResponseEntity<AccountDto> {
        return try {
            val account = accountService.findById(id)

            ResponseEntity.ok(account.toDto())
        } catch (ex: Exception) {
            ResponseEntity.notFound().build()
        }
    }

    @Operation(summary = "Delete an account based on ID")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Deleted the account"),
            ApiResponse(responseCode = "404", description = "Did not find the account"),
            ApiResponse(responseCode = "500", description = "Something went wrong")
        ]
    )
    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        return try {
            accountService.delete(id)

            ResponseEntity.ok().build()
        } catch (ex: java.lang.Exception) {
            ResponseEntity.notFound().build()
        }
    }
}
