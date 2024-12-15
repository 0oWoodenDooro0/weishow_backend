package com.gmail.vincent031525.data.data_source.entity

import org.jetbrains.exposed.sql.Table

object TheaterEntity : Table(name = "theater") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 50)
    val address = varchar("address", 50)
    val phoneNumber = varchar("phone_number", 50)
    val description = text("description")
    override val primaryKey = PrimaryKey(id)
}