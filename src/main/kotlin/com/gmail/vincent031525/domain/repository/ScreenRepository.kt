package com.gmail.vincent031525.domain.repository

import com.gmail.vincent031525.domain.model.ScreenDto
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

interface ScreenRepository {
    suspend fun <T> query(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }
    suspend fun getScreenByTheaterId(id: Int): Result<List<ScreenDto>>
    suspend fun addScreen(screenDto: ScreenDto): Int
}