package com.gmail.vincent031525.data.repository

import com.gmail.vincent031525.data.data_source.dao.ScreenDao
import com.gmail.vincent031525.data.data_source.entity.ScreenEntity
import com.gmail.vincent031525.data.data_source.entity.TheaterEntity
import com.gmail.vincent031525.domain.model.ScreenDto
import com.gmail.vincent031525.domain.repository.ScreenRepository
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class ScreenRepositoryImpl : ScreenRepository {
    override suspend fun getScreenByTheaterId(id: Int): Result<List<ScreenDto>> = try {
        query {
            val data = ScreenDao.find(ScreenEntity.theaterId eq id).map {
                ScreenDto(
                    id = it.id.value,
                    number = it.number,
                    row = it.row,
                    column = it.column,
                    theaterId = it.theaterId.value,
                )
            }
            Result.success(data)
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun addScreen(screenDto: ScreenDto): Int = query {
        ScreenDao.new {
            number = screenDto.number
            row = screenDto.row
            column = screenDto.column
            theaterId = EntityID(screenDto.theaterId, TheaterEntity)
        }.id.value
    }
}