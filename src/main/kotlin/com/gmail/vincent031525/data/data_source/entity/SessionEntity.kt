package com.gmail.vincent031525.data.data_source.entity

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.time

object SessionEntity : IntIdTable(name = "session") {
    val start_time = time("start_time")
    val screenId = reference("screen_id", ScreenEntity.id)
    val movieId = reference("movie_id", MovieEntity.id)
    val removed = bool("removed").default(false)
}

