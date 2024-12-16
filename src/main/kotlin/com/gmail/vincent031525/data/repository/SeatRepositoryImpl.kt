package com.gmail.vincent031525.data.repository

import com.gmail.vincent031525.data.data_source.dao.SeatDao
import com.gmail.vincent031525.domain.model.SeatDto
import com.gmail.vincent031525.domain.repository.SeatRepository

class SeatRepositoryImpl : SeatRepository {
    override suspend fun addSeat(seatDto: SeatDto): Int = query {
        SeatDao.new {
            row = seatDto.row
            column = seatDto.column
            number = seatDto.number
        }.id.value
    }

}