package com.gmail.vincent031525.domain.repository

import com.gmail.vincent031525.domain.model.TicketDto
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

interface TicketRepository {
    suspend fun <T> query(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }
    suspend fun addTicket(ticketDto: TicketDto): Int
    suspend fun updateTicket(id: Int, ticketDto: TicketDto)
    suspend fun getTicketsByMemberId(memberId: Int): List<TicketDto>
}