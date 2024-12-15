package com.gmail.vincent031525.data.data_source.entity

import org.jetbrains.exposed.sql.Table

object ScreenEntity : Table(name = "screen") {
    val id = integer("id").autoIncrement()
    val number = integer("number")
    val row = integer("row")
    val column = integer("column")
    val theater_id = reference("theater_id", TheaterEntity.id)
    override val primaryKey = PrimaryKey(id)
}