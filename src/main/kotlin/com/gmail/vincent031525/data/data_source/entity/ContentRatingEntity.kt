package com.gmail.vincent031525.data.data_source.entity

import org.jetbrains.exposed.sql.Table

object ContentRatingEntity : Table(name = "content_rating") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 50)
    override val primaryKey = PrimaryKey(id)
}