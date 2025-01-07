package com.gmail.vincent031525.data.data_source.entity

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.date
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object TicketEntity : IntIdTable(name = "ticket") {
    val price = integer("price").default(300)
    val purchaseTime = datetime("purchase_time")
    val date = date("date")
    val sessionId = reference("session_id", SessionEntity.id)
    val memberId = reference("member_id", MemberEntity.id).nullable()
    val statusId = reference("status_id", TicketStatusEntity.id)
    val seatId = reference("seat_id", SeatEntity.id)
    val removed = bool("removed").default(false)

    init {
        uniqueIndex(date, sessionId, seatId)
    }
}