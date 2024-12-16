package com.gmail.vincent031525.domain.repository

import com.gmail.vincent031525.domain.model.SeatDto

interface SeatRepository {
    suspend fun addSeat(seatDto: SeatDto): Int
}