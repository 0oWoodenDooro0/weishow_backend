package com.gmail.vincent031525.data.data_source.dao

import com.gmail.vincent031525.data.data_source.entity.SeatEntity
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class SeatDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SeatDao>(SeatEntity)

    var row by SeatEntity.row
    var column by SeatEntity.column
    var number by SeatEntity.number
}