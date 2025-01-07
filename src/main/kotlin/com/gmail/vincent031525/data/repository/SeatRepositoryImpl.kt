package com.gmail.vincent031525.data.repository

import com.gmail.vincent031525.data.data_source.dao.SeatDao
import com.gmail.vincent031525.data.data_source.entity.SeatEntity
import com.gmail.vincent031525.data.data_source.entity.TicketEntity
import com.gmail.vincent031525.domain.model.SeatDto
import com.gmail.vincent031525.domain.model.request.GetSeatBySessionIdRequest
import com.gmail.vincent031525.domain.repository.SeatRepository
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.and

class SeatRepositoryImpl : SeatRepository {
    override suspend fun getSeatBySessionId(sessionId: Int, request: GetSeatBySessionIdRequest): Result<List<SeatDto>> =
        try {
            query {
                val seats = SeatEntity
                    .join(TicketEntity, JoinType.INNER, additionalConstraint = { SeatEntity.id eq TicketEntity.seatId })
                    .select(SeatEntity.columns)
                    .where { (TicketEntity.sessionId eq sessionId) and (TicketEntity.date eq request.date) }
                    .mapNotNull {
                        SeatDto(
                            id = it[SeatEntity.id].value,
                            row = it[SeatEntity.row],
                            column = it[SeatEntity.column],
                            number = it[SeatEntity.number]
                        )
                    }
                Result.success(seats)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }

    override suspend fun addSeat(seatDto: SeatDto): Int = query {
        SeatDao.new {
            row = seatDto.row
            column = seatDto.column
            number = seatDto.number
        }.id.value
    }

}