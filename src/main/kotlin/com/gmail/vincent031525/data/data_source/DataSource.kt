package com.gmail.vincent031525.data.data_source

import com.gmail.vincent031525.data.data_source.dao.*
import com.gmail.vincent031525.data.data_source.entity.*
import com.gmail.vincent031525.domain.model.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

class DataSource(database: Database) {
    init {
        transaction(database) {
            SchemaUtils.createMissingTablesAndColumns(ContentRatingEntity)
            SchemaUtils.createMissingTablesAndColumns(MovieEntity)
            SchemaUtils.createMissingTablesAndColumns(TheaterEntity)
            SchemaUtils.createMissingTablesAndColumns(ManagerEntity)
            SchemaUtils.createMissingTablesAndColumns(ManagementEntity)
            SchemaUtils.createMissingTablesAndColumns(ScreenEntity)
            SchemaUtils.createMissingTablesAndColumns(SessionEntity)
            SchemaUtils.createMissingTablesAndColumns(MemberEntity)
            SchemaUtils.createMissingTablesAndColumns(TicketStatusEntity)
            SchemaUtils.createMissingTablesAndColumns(SeatEntity)
            SchemaUtils.createMissingTablesAndColumns(TicketTypeEntity)
            SchemaUtils.createMissingTablesAndColumns(TicketEntity)
        }
    }

    private suspend fun <T> query(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }
    suspend fun getAllMovies(): List<MovieDto> = query {
        MovieDao.all().map {
            MovieDto(
                it.id.value,
                it.name,
                it.releaseTime,
                it.duartionMin,
                it.description,
                it.thumbnailPath,
                it.contentRatingId.value
            )
        }
    }

    suspend fun addMovie(movieDto: MovieDto): Int = query {
        MovieDao.new {
            name = movieDto.name
            releaseTime = movieDto.releaseDate
            duartionMin = movieDto.durationMin
            description = movieDto.description
            thumbnailPath = movieDto.thumbnailPath
            contentRatingId = EntityID(movieDto.contentRatingId, ContentRatingEntity)
        }.id.value
    }

    suspend fun addTheater(theaterDto: TheaterDto): Int = query {
        TheaterDao.new {
            name = theaterDto.name
            address = theaterDto.address
            phoneNumber = theaterDto.phoneNumber
            description = theaterDto.description
        }.id.value
    }

    suspend fun addScreen(screenDto: ScreenDto): Int = query {
        ScreenDao.new {
            number = screenDto.number
            row = screenDto.row
            column = screenDto.column
            theaterId = EntityID(screenDto.theaterId, TheaterEntity)
        }.id.value
    }

    suspend fun addSession(sessionDto: SessionDto): Int = query {
        SessionDao.new {
            price = sessionDto.price
            screenId = EntityID(sessionDto.screenId, ScreenEntity)
            movieId = EntityID(sessionDto.movieId, MovieEntity)
        }.id.value
    }

    suspend fun addSeat(seatDto: SeatDto): Int = query {
        SeatDao.new {
            row = seatDto.row
            column = seatDto.column
            number = seatDto.number
        }.id.value
    }

    suspend fun addTicket(ticketDto: TicketDto): Int = query {
        TicketDao.new {
            price = ticketDto.price
            purchaseTime = ticketDto.purchseTime
            sessionId = EntityID(ticketDto.sessionId, SessionEntity)
            memberId = EntityID(ticketDto.memberId, MemberEntity)
            statusId = EntityID(ticketDto.statusId, TicketStatusEntity)
            typeId = EntityID(ticketDto.typeId, TicketTypeEntity)
        }.id.value
    }

    suspend fun getTicketsByMemberId(memberId: Int): List<TicketDto> = query {
        TicketDao.find(TicketEntity.memberId eq memberId).map {
            TicketDto(
                id = it.id.value,
                price = it.price,
                purchseTime = it.purchaseTime,
                sessionId = it.sessionId.value,
                memberId = it.memberId.value,
                statusId = it.statusId.value,
                seatId = it.seatId.value,
                typeId = it.typeId.value
            )
        }
    }

    suspend fun addMember(memberDto: MemberDto): Int = query {
        MemberDao.new {
            name = memberDto.name
            email = memberDto.email
            password = memberDto.password
            gender = memberDto.gender
        }.id.value
    }
}