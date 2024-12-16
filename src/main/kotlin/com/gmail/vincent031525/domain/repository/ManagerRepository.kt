package com.gmail.vincent031525.domain.repository

import com.gmail.vincent031525.domain.model.ManagerDto
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

interface ManagerRepository {
    suspend fun <T> query(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }
    suspend fun addManager(managerDto: ManagerDto): Int
}