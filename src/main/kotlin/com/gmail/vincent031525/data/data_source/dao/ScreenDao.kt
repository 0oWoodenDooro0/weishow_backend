package com.gmail.vincent031525.data.data_source.dao

import com.gmail.vincent031525.data.data_source.entity.ScreenEntity
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ScreenDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ScreenDao>(ScreenEntity)

    var number by ScreenEntity.number
    var row by ScreenEntity.row
    var column by ScreenEntity.column
    var theaterId by ScreenEntity.theaterId
}