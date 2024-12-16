package com.gmail.vincent031525.data.data_source.entity

import org.jetbrains.exposed.dao.id.IntIdTable

object ManagementEntity : IntIdTable(name = "Management") {
    val managerId = reference("manager_id", ManagerEntity.id)
    val theaterId = reference("theater_id", TheaterEntity.id)
}