package com.gmail.vincent031525.data.data_source.dao

import com.gmail.vincent031525.data.data_source.entity.ContentRatingEntity
import com.gmail.vincent031525.data.data_source.entity.MovieEntity
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ContentRatingDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ContentRatingDao>(ContentRatingEntity)

    var name by ContentRatingEntity.name
    val movie by MovieDao referrersOn MovieEntity.contentRatingId
}