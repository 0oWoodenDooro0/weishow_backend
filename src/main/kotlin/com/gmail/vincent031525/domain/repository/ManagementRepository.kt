package com.gmail.vincent031525.domain.repository

import com.gmail.vincent031525.domain.model.ManagementDto
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

interface ManagementRepository {
    suspend fun <T> query(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }
    suspend fun addManagement(managementDto: ManagementDto): Int
}