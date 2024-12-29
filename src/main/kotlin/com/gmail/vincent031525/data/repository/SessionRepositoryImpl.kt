package com.gmail.vincent031525.data.repository

import com.gmail.vincent031525.data.data_source.dao.SessionDao
import com.gmail.vincent031525.data.data_source.entity.MovieEntity
import com.gmail.vincent031525.data.data_source.entity.ScreenEntity
import com.gmail.vincent031525.data.data_source.entity.SessionEntity
import com.gmail.vincent031525.domain.model.SessionDto
import com.gmail.vincent031525.domain.repository.SessionRepository
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class SessionRepositoryImpl : SessionRepository {
    override suspend fun addSession(sessionDto: SessionDto): Int = query {
        SessionDao.new {
            price = sessionDto.price
            screenId = EntityID(sessionDto.screenId, ScreenEntity)
            movieId = EntityID(sessionDto.movieId, MovieEntity)
        }.id.value
    }

    override suspend fun getSessionByMovieId(movieId: Int): List<SessionDto> = query {
        SessionDao.find(SessionEntity.movieId eq movieId).map {
            SessionDto(
                id = it.id.value,
                price = it.price,
                startTime = it.startTime,
                screenId = it.screenId.value,
                movieId = it.movieId.value
            )
        }
    }
}