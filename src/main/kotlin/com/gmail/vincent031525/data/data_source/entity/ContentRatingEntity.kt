package com.gmail.vincent031525.data.data_source.entity

import org.jetbrains.exposed.dao.id.IntIdTable

object ContentRatingEntity : IntIdTable(name = "content_rating") {
    val name = varchar("name", 50)
}