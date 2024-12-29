package com.gmail.vincent031525.domain.repository

import com.gmail.vincent031525.domain.model.MovieDto
import com.gmail.vincent031525.domain.model.request.AddMovieRequest
import com.gmail.vincent031525.domain.model.request.UpdateMovieRequest
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

interface MovieRepository {
    suspend fun <T> query(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }
    suspend fun getAllMovies(): Result<List<MovieDto>>
    suspend fun addMovie(addMovieRequest: AddMovieRequest): Result<Int>
    suspend fun updateMovie(id: Int, updateMovieRequest: UpdateMovieRequest): Result<Int>
    suspend fun deleteMovie(id: Int): Result<Int>
}