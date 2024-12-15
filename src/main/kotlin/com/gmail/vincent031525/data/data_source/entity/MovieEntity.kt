package com.gmail.vincent031525.data.data_source.entity

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.date

object MovieEntity : Table(name = "movie") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 50)
    val releaseTime = date("release_time")
    val director = varchar("director", 50)
    val actors = varchar("actors", 200)
    val length = integer("length")
    val description = text("description")
    val thumbnailPath = varchar("thumbnail_path", 50)
    val contentRatingId = reference("content_rating_id", ContentRatingEntity.id)
    override val primaryKey = PrimaryKey(id)
}