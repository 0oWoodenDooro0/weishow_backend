package com.gmail.vincent031525.domain.repository

import com.gmail.vincent031525.domain.model.SessionDto
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

interface SessionRepository {
    suspend fun <T> query(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }
    suspend fun addSession(sessionDto: SessionDto): Int
    suspend fun getSessionByMovieId(movieId: Int): List<SessionDto>
    suspend fun getSessionByTheaterIdAndMovieId(theaterId :Int, movieId: Int): Result<List<SessionDto>>
}