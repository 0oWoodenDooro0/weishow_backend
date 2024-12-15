package com.gmail.vincent031525.data.data_source.entity

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object TicketEntity : Table(name = "ticket") {
    val id = integer("id").autoIncrement()
    val price = integer("price")
    val purchaseTime = datetime("purchase_time")
    val sessionId = reference("session_id", SessionEntity.id)
    val memberId = reference("member_id", MemberEntity.id)
    val statusId = reference("status_id", TicketStatusEntity.id)
    val seatId = reference("seat_id", SeatEntity.id)
    val typeId = reference("type_id", TicketTypeEntity.id)
}