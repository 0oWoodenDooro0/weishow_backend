package com.gmail.vincent031525.data.data_source.entity

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date

object MovieEntity : IntIdTable(name = "movie") {
    val name = varchar("name", 50)
    val releaseDate = date("release_date")
    var durationMin = integer("duration_min")
    val description = text("description")
    val thumbnailPath = varchar("thumbnail_path", 50)
    val contentRatingId = reference("content_rating_id", ContentRatingEntity.id)
    val removed = bool("removed").default(false)
}
