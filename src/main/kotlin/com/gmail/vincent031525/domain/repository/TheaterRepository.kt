package com.gmail.vincent031525.domain.repository

import com.gmail.vincent031525.domain.model.TheaterDto

interface TheaterRepository {
    suspend fun addTheater(theaterDto: TheaterDto): Int
}
