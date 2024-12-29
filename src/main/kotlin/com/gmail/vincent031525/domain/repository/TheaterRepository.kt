package com.gmail.vincent031525.domain.repository

import com.gmail.vincent031525.domain.model.TheaterDto
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

interface TheaterRepository {
    suspend fun <T> query(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }
    suspend fun getTheater(): Result<List<TheaterDto>>
    suspend fun getTheatersByManagerId(id: Int): Result<List<TheaterDto>>
    suspend fun addTheater(theaterDto: TheaterDto): Int
}
