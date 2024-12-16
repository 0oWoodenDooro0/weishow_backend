package com.gmail.vincent031525.data.data_source.entity

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object SessionEntity : IntIdTable(name = "session") {
    val start_time = datetime("start_time")
    val price = integer("price")
    val screenId = reference("screen_id", ScreenEntity.id)
    val movieId = reference("movie_id", MovieEntity.id)
}

