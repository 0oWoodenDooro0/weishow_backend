package com.gmail.vincent031525.data.data_source

import com.gmail.vincent031525.domain.model.MovieDto
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

class LocalDataSource(database: Database) {
    init {
        transaction(database) {
            SchemaUtils.createMissingTablesAndColumns(MovieEntity)
        }
    }

    private suspend fun <T> query(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }
    suspend fun getAllMovies(): List<MovieDto> {
        return query {
            MovieEntity.selectAll()
                .mapNotNull { MovieDto(it[MovieEntity.id], it[MovieEntity.name], it[MovieEntity.thumbnailPath]) }
        }
    }
}