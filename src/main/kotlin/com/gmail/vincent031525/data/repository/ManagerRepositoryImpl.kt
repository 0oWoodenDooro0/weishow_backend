package com.gmail.vincent031525.data.repository

import com.gmail.vincent031525.data.data_source.dao.ManagerDao
import com.gmail.vincent031525.domain.model.ManagerDto
import com.gmail.vincent031525.domain.repository.ManagerRepository

class ManagerRepositoryImpl : ManagerRepository {
    override suspend fun addManager(managerDto: ManagerDto): Int = query {
        ManagerDao.new {
            username = managerDto.username
            password = managerDto.password
        }.id.value
    }
}