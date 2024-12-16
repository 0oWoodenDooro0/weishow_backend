package com.gmail.vincent031525.data.data_source.dao

import com.gmail.vincent031525.data.data_source.entity.MovieEntity
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class MovieDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<MovieDao>(MovieEntity)

    var name by MovieEntity.name
    var releaseTime by MovieEntity.releaseTime
    var director by MovieEntity.director
    var actors by MovieEntity.actors
    var length by MovieEntity.length
    var description by MovieEntity.description
    var thumbnailPath by MovieEntity.thumbnailPath
    var contentRatingId by MovieEntity.contentRatingId
}