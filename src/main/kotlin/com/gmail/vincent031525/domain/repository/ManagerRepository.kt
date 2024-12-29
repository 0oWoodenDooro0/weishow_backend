package com.gmail.vincent031525.domain.repository

import com.gmail.vincent031525.domain.model.ManagerDto
import com.gmail.vincent031525.domain.model.request.ManagerLoginRequest
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

interface ManagerRepository {
    suspend fun <T> query(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }
    suspend fun addManager(managerDto: ManagerDto): Int
    suspend fun login(managerLoginRequest: ManagerLoginRequest): Result<ManagerDto>
}