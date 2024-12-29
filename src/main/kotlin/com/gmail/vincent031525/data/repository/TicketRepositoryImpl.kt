package com.gmail.vincent031525.data.repository

import com.gmail.vincent031525.data.data_source.dao.TicketDao
import com.gmail.vincent031525.data.data_source.entity.*
import com.gmail.vincent031525.domain.model.TicketDto
import com.gmail.vincent031525.domain.repository.TicketRepository
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class TicketRepositoryImpl : TicketRepository {
    override suspend fun addTicket(ticketDto: TicketDto): Int = query {
        TicketDao.new {
            price = ticketDto.price
            purchaseTime = ticketDto.purchseTime
            date = ticketDto.date
            sessionId = EntityID(ticketDto.sessionId, SessionEntity)
            memberId = EntityID(ticketDto.memberId, MemberEntity)
            statusId = EntityID(ticketDto.statusId, TicketStatusEntity)
            typeId = EntityID(ticketDto.typeId, TicketTypeEntity)
        }.id.value
    }

    override suspend fun updateTicket(id: Int, ticketDto: TicketDto) {
        query {
            TicketDao.findByIdAndUpdate(id) {
                it.price = ticketDto.price
                it.purchaseTime = ticketDto.purchseTime
                it.date = ticketDto.date
                it.sessionId = EntityID(ticketDto.sessionId, SessionEntity)
                it.memberId = EntityID(ticketDto.memberId, MemberEntity)
                it.statusId = EntityID(ticketDto.statusId, TicketStatusEntity)
                it.typeId = EntityID(ticketDto.typeId, TicketTypeEntity)
            }
        }
    }

    override suspend fun getTicketsByMemberId(memberId: Int): List<TicketDto> = query {
        TicketDao.find(TicketEntity.memberId eq memberId).map {
            TicketDto(
                id = it.id.value,
                price = it.price,
                purchseTime = it.purchaseTime,
                date = it.date,
                sessionId = it.sessionId.value,
                memberId = it.memberId.value,
                statusId = it.statusId.value,
                seatId = it.seatId.value,
                typeId = it.typeId.value
            )
        }
    }
}