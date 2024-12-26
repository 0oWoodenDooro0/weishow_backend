package com.gmail.vincent031525.data.repository

import com.gmail.vincent031525.data.data_source.dao.ManagementDao
import com.gmail.vincent031525.data.data_source.entity.ManagementEntity
import com.gmail.vincent031525.domain.model.ManagementDto
import com.gmail.vincent031525.domain.repository.ManagementRepository
import org.jetbrains.exposed.dao.id.EntityID

class ManagementRepositoryImpl : ManagementRepository {
    override suspend fun addManagement(managementDto: ManagementDto): Result<Int> = query {
        ManagementDao.new {
            managerId = EntityID(managementDto.managerId, ManagementEntity)
            theaterId = EntityID(managementDto.theaterId, ManagementEntity)
        }.id.let {
            Result.success(it.value)
        }
    }
}