package com.gmail.vincent031525.domain.repository

import com.gmail.vincent031525.domain.model.SeatDto
import com.gmail.vincent031525.domain.model.request.GetSeatBySessionIdRequest
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

interface SeatRepository {
    suspend fun <T> query(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }
    suspend fun getSeatBySessionId(sessionId: Int, request: GetSeatBySessionIdRequest): Result<List<SeatDto>>
    suspend fun addSeat(seatDto: SeatDto): Int
}