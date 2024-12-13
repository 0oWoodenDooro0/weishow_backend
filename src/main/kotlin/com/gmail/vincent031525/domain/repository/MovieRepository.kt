package com.gmail.vincent031525.domain.repository

import com.gmail.vincent031525.domain.model.MovieDto
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

interface MovieRepository {
    suspend fun <T> query(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }
    suspend fun getAllMovies(): List<MovieDto>
    suspend fun addMovie(movieDto: MovieDto): Int
    suspend fun updateMovie(id: Int, movieDto: MovieDto)
}