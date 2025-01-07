package com.gmail.vincent031525.data.repository

import com.gmail.vincent031525.data.data_source.dao.SeatDao
import com.gmail.vincent031525.data.data_source.dao.TicketDao
import com.gmail.vincent031525.data.data_source.entity.*
import com.gmail.vincent031525.domain.model.request.AddTicketRequest
import com.gmail.vincent031525.domain.model.response.TicketResponse
import com.gmail.vincent031525.domain.repository.TicketRepository
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class TicketRepositoryImpl : TicketRepository {
    override suspend fun addTicket(request: AddTicketRequest): Result<Int> = try {
        query {
            val seatID = SeatDao.find(SeatEntity.number eq request.seatNumber).single().id
            val memberID = request.memberId?.let {
                EntityID(it, MemberEntity)
            }
            val id = TicketDao.new {
                purchaseTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
                date = request.date
                sessionId = EntityID(request.sessionId, SessionEntity)
                memberId = memberID
                statusId = EntityID(1, TicketStatusEntity)
                seatId = seatID
            }.id.value
            Result.success(id)
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun deleteTicket(id: Int): Result<Int> = try {
        query {
            TicketDao.findByIdAndUpdate(id) {
                it.removed = true
            }
            Result.success(id)
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getTicketsByMemberId(memberId: Int): Result<List<TicketResponse>> = try {
        query {
            val tickets = TicketEntity.join(
                SessionEntity,
                JoinType.INNER,
                additionalConstraint = { TicketEntity.sessionId eq SessionEntity.id })
                .join(SeatEntity, JoinType.INNER, additionalConstraint = { TicketEntity.seatId eq SeatEntity.id })
                .join(MovieEntity, JoinType.INNER, additionalConstraint = { SessionEntity.movieId eq MovieEntity.id })
                .join(
                    ScreenEntity,
                    JoinType.INNER,
                    additionalConstraint = { SessionEntity.screenId eq ScreenEntity.id })
                .join(
                    TheaterEntity,
                    JoinType.INNER,
                    additionalConstraint = { ScreenEntity.theaterId eq TheaterEntity.id })
                .select(
                    TicketEntity.id,
                    TicketEntity.date,
                    TheaterEntity.name,
                    ScreenEntity.number,
                    SessionEntity.start_time,
                    MovieEntity.name,
                    TicketEntity.price,
                    SeatEntity.number
                ).where { TicketEntity.memberId eq memberId }.mapNotNull {
                    TicketResponse(
                        id = it[TicketEntity.id].value,
                        date = it[TicketEntity.date],
                        theaterName = it[TheaterEntity.name],
                        screenNumber = it[ScreenEntity.number],
                        startTime = it[SessionEntity.start_time],
                        movieName = it[MovieEntity.name],
                        price = it[TicketEntity.price],
                        seatNumber = it[SeatEntity.number]
                    )
                }
            Result.success(tickets)
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}