package com.gmail.vincent031525.data.repository

import com.gmail.vincent031525.data.data_source.DataSource
import com.gmail.vincent031525.domain.model.TicketDto
import com.gmail.vincent031525.domain.repository.TicketRepository

class TicketRepositoryImpl(private val dataSource: DataSource) : TicketRepository {
    override suspend fun addTicket(ticketDto: TicketDto): Int = dataSource.addTicket(ticketDto)
    override suspend fun getTicketsByMemberId(memberId: Int): List<TicketDto> =
        dataSource.getTicketsByMemberId(memberId)
}