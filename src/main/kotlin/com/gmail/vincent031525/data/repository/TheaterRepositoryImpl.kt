package com.gmail.vincent031525.data.repository

import com.gmail.vincent031525.data.data_source.dao.TheaterDao
import com.gmail.vincent031525.domain.model.TheaterDto
import com.gmail.vincent031525.domain.repository.TheaterRepository

class TheaterRepositoryImpl : TheaterRepository {
    override suspend fun addTheater(theaterDto: TheaterDto): Int = query {
        TheaterDao.new {
            name = theaterDto.name
            address = theaterDto.address
            phoneNumber = theaterDto.phoneNumber
            description = theaterDto.description
        }.id.value
    }

}