package com.gmail.vincent031525.data.data_source.entity

import org.jetbrains.exposed.dao.id.IntIdTable

object ScreenEntity : IntIdTable(name = "screen") {
    val number = integer("number")
    val row = integer("row")
    val column = integer("column")
    val theaterId = reference("theater_id", TheaterEntity.id)

    init {
        uniqueIndex(number, theaterId)
    }
}