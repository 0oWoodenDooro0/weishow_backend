package com.gmail.vincent031525.domain.repository

import com.gmail.vincent031525.domain.model.ScreenDto

interface ScreenRepository {
    suspend fun addScreen(screenDto: ScreenDto): Int
}