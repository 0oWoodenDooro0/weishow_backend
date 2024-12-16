package com.gmail.vincent031525.data.data_source.dao

import com.gmail.vincent031525.data.data_source.entity.TheaterEntity
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class TheaterDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TheaterDao>(TheaterEntity)

    var name by TheaterEntity.name
    var address by TheaterEntity.address
    var phoneNumber by TheaterEntity.phoneNumber
    var description by TheaterEntity.description
}