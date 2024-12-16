package com.gmail.vincent031525.data.data_source.dao

import com.gmail.vincent031525.data.data_source.entity.TicketStatusEntity
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class TicketStatusDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TicketStatusDao>(TicketStatusEntity)

    var name by TicketStatusEntity.name
}