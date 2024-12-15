package com.gmail.vincent031525.data.data_source

import com.gmail.vincent031525.data.data_source.entity.*
import com.gmail.vincent031525.domain.model.MovieDto
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
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
        MovieEntity.insert {
            it[name] = movieDto.name
            it[releaseTime] = movieDto.releaseDate
            it[director] = movieDto.director
            it[actors] = movieDto.actors.joinToString()
            it[length] = movieDto.length
            it[description] = movieDto.description
            it[thumbnailPath] = movieDto.thumbnailPath
            it[contentRatingId] = movieDto.contentRatingId
        }
    }
}