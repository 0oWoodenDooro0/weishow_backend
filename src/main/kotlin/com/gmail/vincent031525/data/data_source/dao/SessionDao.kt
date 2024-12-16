package com.gmail.vincent031525.data.data_source.dao

import com.gmail.vincent031525.data.data_source.entity.SessionEntity
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class SessionDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SessionDao>(SessionEntity)

    var start by SessionEntity.start_time
    var price by SessionEntity.price
    var screenId by SessionEntity.screenId
    var movieId by SessionEntity.movieId
}