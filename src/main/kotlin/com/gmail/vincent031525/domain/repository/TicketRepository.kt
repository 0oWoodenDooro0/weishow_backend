package com.gmail.vincent031525.domain.repository

import com.gmail.vincent031525.domain.model.request.AddTicketRequest
import com.gmail.vincent031525.domain.model.response.TicketResponse
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

interface TicketRepository {
    suspend fun <T> query(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }
    suspend fun addTicket(request: AddTicketRequest): Result<Int>
    suspend fun deleteTicket(id: Int): Result<Int>
    suspend fun getTicketsByMemberId(memberId: Int): Result<List<TicketResponse>>
}