package com.gmail.vincent031525.data.repository

import com.gmail.vincent031525.data.data_source.DataSource
import com.gmail.vincent031525.domain.model.TheaterDto
import com.gmail.vincent031525.domain.repository.TheaterRepository

class TheaterRepositoryImpl(private val dataSource: DataSource) : TheaterRepository {
    override suspend fun addTheater(theaterDto: TheaterDto): Int = dataSource.addTheater(theaterDto)
}