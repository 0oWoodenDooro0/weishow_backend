package com.gmail.vincent031525.data.data_source.dao

import com.gmail.vincent031525.data.data_source.entity.ManagementEntity
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ManagementDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ManagementDao>(ManagementEntity)

    var managerId by ManagementEntity.managerId
    var theaterId by ManagementEntity.theaterId
}