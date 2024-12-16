package com.gmail.vincent031525.data.data_source.entity

import org.jetbrains.exposed.dao.id.IntIdTable

object TheaterEntity : IntIdTable(name = "theater") {
    val name = varchar("name", 50)
    val address = varchar("address", 50)
    val phoneNumber = varchar("phone_number", 50)
    val description = text("description")
}