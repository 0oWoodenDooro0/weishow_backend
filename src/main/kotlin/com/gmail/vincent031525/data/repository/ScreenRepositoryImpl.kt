package com.gmail.vincent031525.data.repository

import com.gmail.vincent031525.data.data_source.dao.ScreenDao
import com.gmail.vincent031525.data.data_source.entity.TheaterEntity
import com.gmail.vincent031525.domain.model.ScreenDto
import com.gmail.vincent031525.domain.repository.ScreenRepository
import org.jetbrains.exposed.dao.id.EntityID

class ScreenRepositoryImpl : ScreenRepository {
    override suspend fun addScreen(screenDto: ScreenDto): Int = query {
        ScreenDao.new {
            number = screenDto.number
            row = screenDto.row
            column = screenDto.column
            theaterId = EntityID(screenDto.theaterId, TheaterEntity)
        }.id.value
    }

}