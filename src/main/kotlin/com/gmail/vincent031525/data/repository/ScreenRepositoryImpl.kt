package com.gmail.vincent031525.data.repository

import com.gmail.vincent031525.data.data_source.DataSource
import com.gmail.vincent031525.domain.model.ScreenDto
import com.gmail.vincent031525.domain.repository.ScreenRepository

class ScreenRepositoryImpl(private val dataSource: DataSource) : ScreenRepository {
    override suspend fun addScreen(screenDto: ScreenDto): Int = dataSource.addScreen(screenDto)
}