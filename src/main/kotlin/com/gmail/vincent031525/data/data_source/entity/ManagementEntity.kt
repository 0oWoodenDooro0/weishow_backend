package com.gmail.vincent031525.data.data_source.entity

import org.jetbrains.exposed.sql.Table

object ManagementEntity : Table(name = "Management") {
    val id = integer("id").autoIncrement()
    val managerId = reference("manager_id", ManagerEntity.id)
    val theaterId = reference("theater_id", TheaterEntity.id)
    override val primaryKey = PrimaryKey(id)
}