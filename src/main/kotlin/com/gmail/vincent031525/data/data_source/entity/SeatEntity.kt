package com.gmail.vincent031525.data.data_source.entity

import org.jetbrains.exposed.sql.Table

object SeatEntity : Table(name = "seat") {
    val id = integer("id").autoIncrement()
    val row = integer("row")
    val column = integer("column")
    val number = varchar("number", 50)
    override val primaryKey = PrimaryKey(id)
}