package com.gmail.vincent031525.data.data_source

import com.gmail.vincent031525.data.data_source.dao.MovieDao
import com.gmail.vincent031525.data.data_source.entity.*
import com.gmail.vincent031525.domain.model.MovieDto
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
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
        TODO()
    }

    suspend fun addMovie(movieDto: MovieDto) = query {
        MovieDao.new {
            name = movieDto.name
            releaseTime = movieDto.releaseDate
            director = movieDto.director
            actors = movieDto.actors.joinToString()
            length = movieDto.length
            description = movieDto.description
            thumbnailPath = movieDto.thumbnailPath
            contentRatingId = EntityID(movieDto.contentRatingId, ContentRatingEntity)
        }
    }
}