package com.gmail.vincent031525.data.repository

import com.gmail.vincent031525.data.data_source.DataSource
import com.gmail.vincent031525.domain.model.SeatDto
import com.gmail.vincent031525.domain.repository.SeatRepository

class SeatRepositoryImpl(private val dataSource: DataSource) : SeatRepository {
    override suspend fun addSeat(seatDto: SeatDto): Int = dataSource.addSeat(seatDto)
}