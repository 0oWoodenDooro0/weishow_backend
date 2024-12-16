package com.gmail.vincent031525.data.data_source.entity

import org.jetbrains.exposed.dao.id.IntIdTable

object TicketStatusEntity : IntIdTable(name = "ticket_status") {
    val name = varchar("name", 50)
}