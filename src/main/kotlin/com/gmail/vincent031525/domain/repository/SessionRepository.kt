package com.gmail.vincent031525.domain.repository

import com.gmail.vincent031525.domain.model.SessionDto

interface SessionRepository {
    suspend fun addSession(sessionDto: SessionDto): Int
}