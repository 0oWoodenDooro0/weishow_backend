package com.gmail.vincent031525.data.repository

import com.gmail.vincent031525.data.data_source.dao.SessionDao
import com.gmail.vincent031525.data.data_source.entity.MovieEntity
import com.gmail.vincent031525.data.data_source.entity.ScreenEntity
import com.gmail.vincent031525.data.data_source.entity.SessionEntity
import com.gmail.vincent031525.data.data_source.entity.TheaterEntity
import com.gmail.vincent031525.domain.model.SessionDto
import com.gmail.vincent031525.domain.model.request.AddSessionRequest
import com.gmail.vincent031525.domain.model.request.GetSessionByTheaterIdAndMovieIdRequest
import com.gmail.vincent031525.domain.model.request.UpdateSessionRequest
import com.gmail.vincent031525.domain.repository.SessionRepository
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and

class SessionRepositoryImpl : SessionRepository {
    override suspend fun addSession(request: AddSessionRequest): Result<Int> = try {
        query {
            val id = SessionDao.new {
                screenId = EntityID(request.screenId, ScreenEntity)
                movieId = EntityID(request.movieId, MovieEntity)
                startTime = request.startTime
            }.id.value
            Result.success(id)
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getSessionByMovieId(movieId: Int): List<SessionDto> = query {
        SessionDao.find((SessionEntity.movieId eq movieId) and (SessionEntity.removed eq false)).map {
            SessionDto(
                id = it.id.value,
                startTime = it.startTime,
                screenId = it.screenId.value,
                movieId = it.movieId.value
            )
        }
    }

    override suspend fun getSessionByTheaterIdAndMovieId(request: GetSessionByTheaterIdAndMovieIdRequest): Result<List<SessionDto>> =
        try {
            query {
                val sessions = SessionEntity
                    .join(
                        ScreenEntity,
                        JoinType.INNER,
                        additionalConstraint = { SessionEntity.screenId eq ScreenEntity.id })
                    .join(
                        TheaterEntity,
                        JoinType.INNER,
                        additionalConstraint = { ScreenEntity.theaterId eq TheaterEntity.id })
                    .select(SessionEntity.columns)
                    .where { (TheaterEntity.id eq request.theaterId) and (SessionEntity.movieId eq request.movieId) }
                    .orderBy(SessionEntity.start_time)
                    .mapNotNull {
                        SessionDto(
                            id = it[SessionEntity.id].value,
                            startTime = it[SessionEntity.start_time],
                            screenId = it[SessionEntity.screenId].value,
                            movieId = it[SessionEntity.movieId].value,
                        )
                    }
                Result.success(sessions)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }

    override suspend fun updateSession(id: Int, request: UpdateSessionRequest): Result<Int> = try {
        query {
            SessionDao.findById(id)?.apply {
                request.startTime?.let { startTime = it }
                request.screenId?.let { screenId = EntityID(it, ScreenEntity) }
                request.movieId?.let { movieId = EntityID(it, MovieEntity) }
            }
            Result.success(id)
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun deleteSession(id: Int): Result<Int> = try {
        query {
            SessionDao.findByIdAndUpdate(id) {
                it.removed = true
            }
            Result.success(id)
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}