package com.gmail.vincent031525.data.data_source.entity

import org.jetbrains.exposed.dao.id.IntIdTable

object SeatEntity : IntIdTable(name = "seat") {
    val row = integer("row")
    val column = integer("column")
    val number = varchar("number", 50)

    init {
        uniqueIndex(row, column)
        uniqueIndex(number)
    }
}