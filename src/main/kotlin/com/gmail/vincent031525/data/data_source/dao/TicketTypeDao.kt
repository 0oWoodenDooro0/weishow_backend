package com.gmail.vincent031525.data.data_source.dao

import com.gmail.vincent031525.data.data_source.entity.TicketTypeEntity
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class TicketTypeDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TicketTypeDao>(TicketTypeEntity)

    var name by TicketTypeEntity.name
}