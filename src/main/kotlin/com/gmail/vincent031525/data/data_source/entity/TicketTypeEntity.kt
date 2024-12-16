package com.gmail.vincent031525.data.data_source.entity

import org.jetbrains.exposed.dao.id.IntIdTable

object TicketTypeEntity : IntIdTable(name = "ticket_type") {
    val name = varchar("name", 50)
}