package com.gmail.vincent031525.domain.repository

import com.gmail.vincent031525.domain.model.SessionDto
import com.gmail.vincent031525.domain.model.request.AddSessionRequest
import com.gmail.vincent031525.domain.model.request.GetSessionByTheaterIdAndMovieIdRequest
import com.gmail.vincent031525.domain.model.request.UpdateSessionRequest
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

interface SessionRepository {
    suspend fun <T> query(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }
    suspend fun addSession(request: AddSessionRequest): Result<Int>
    suspend fun getSessionByMovieId(movieId: Int): List<SessionDto>
    suspend fun getSessionByTheaterIdAndMovieId(request: GetSessionByTheaterIdAndMovieIdRequest): Result<List<SessionDto>>
    suspend fun updateSession(id: Int, request: UpdateSessionRequest): Result<Int>
    suspend fun deleteSession(id: Int): Result<Int>
}