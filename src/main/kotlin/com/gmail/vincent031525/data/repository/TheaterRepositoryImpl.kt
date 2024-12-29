package com.gmail.vincent031525.data.repository

import com.gmail.vincent031525.data.data_source.dao.ManagementDao
import com.gmail.vincent031525.data.data_source.dao.TheaterDao
import com.gmail.vincent031525.data.data_source.entity.ManagementEntity
import com.gmail.vincent031525.domain.model.TheaterDto
import com.gmail.vincent031525.domain.repository.TheaterRepository
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class TheaterRepositoryImpl : TheaterRepository {
    override suspend fun getTheater(): Result<List<TheaterDto>> = try {
        query {
            val result = TheaterDao.all().map {
                TheaterDto(
                    id = it.id.value,
                    name = it.name,
                    address = it.address,
                    phoneNumber = it.phoneNumber,
                    description = it.description
                )
            }
            Result.success(result)
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getTheatersByManagerId(id: Int): Result<List<TheaterDto>> = try {
        query {
            val result = mutableListOf<TheaterDto>()
            ManagementDao.find(ManagementEntity.managerId eq id).forEach { management ->
                val theater = TheaterDao.findById(management.theaterId)
                theater?.let {
                    result.add(
                        TheaterDto(
                            id = it.id.value,
                            name = it.name,
                            address = it.address,
                            phoneNumber = it.phoneNumber,
                            description = it.description
                        )
                    )
                }
            }
            Result.success(result)
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun addTheater(theaterDto: TheaterDto): Int = query {
        TheaterDao.new {
            name = theaterDto.name
            address = theaterDto.address
            phoneNumber = theaterDto.phoneNumber
            description = theaterDto.description
        }.id.value
    }

}