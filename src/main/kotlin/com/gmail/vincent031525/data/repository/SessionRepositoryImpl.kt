package com.gmail.vincent031525.data.repository

import com.gmail.vincent031525.data.data_source.DataSource
import com.gmail.vincent031525.domain.model.SessionDto
import com.gmail.vincent031525.domain.repository.SessionRepository

class SessionRepositoryImpl(private val dataSource: DataSource) : SessionRepository {
    override suspend fun addSession(sessionDto: SessionDto): Int = dataSource.addSession(sessionDto)
}