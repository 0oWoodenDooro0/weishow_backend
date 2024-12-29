package com.gmail.vincent031525.data.repository

import com.gmail.vincent031525.data.data_source.dao.ManagerDao
import com.gmail.vincent031525.data.data_source.entity.ManagerEntity
import com.gmail.vincent031525.domain.model.ManagerDto
import com.gmail.vincent031525.domain.model.request.ManagerLoginRequest
import com.gmail.vincent031525.domain.repository.ManagerRepository
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and

class ManagerRepositoryImpl : ManagerRepository {
    override suspend fun addManager(managerDto: ManagerDto): Int = query {
        ManagerDao.new {
            username = managerDto.username
            password = managerDto.password
        }.id.value
    }

    override suspend fun login(managerLoginRequest: ManagerLoginRequest): Result<ManagerDto> = try {
        val result = query {
            ManagerDao.find((ManagerEntity.username eq managerLoginRequest.username) and (ManagerEntity.password eq managerLoginRequest.password))
                .map { ManagerDto(id = it.id.value, username = it.username, password = it.password) }.single()
        }
        Result.success(result)
    } catch (e: Exception) {
        Result.failure(e)
    }
}