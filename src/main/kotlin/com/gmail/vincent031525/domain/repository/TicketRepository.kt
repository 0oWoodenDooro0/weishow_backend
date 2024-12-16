package com.gmail.vincent031525.domain.repository

import com.gmail.vincent031525.domain.model.TicketDto

interface TicketRepository {
    suspend fun addTicket(ticketDto: TicketDto): Int
    suspend fun getTicketsByMemberId(memberId: Int): List<TicketDto>
}