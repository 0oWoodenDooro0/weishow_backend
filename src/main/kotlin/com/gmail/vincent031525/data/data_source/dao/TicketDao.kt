package com.gmail.vincent031525.data.data_source.dao

import com.gmail.vincent031525.data.data_source.entity.TicketEntity
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class TicketDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TicketDao>(TicketEntity)

    var price by TicketEntity.price
    var purchaseTime by TicketEntity.purchaseTime
    var date by TicketEntity.date
    var sessionId by TicketEntity.sessionId
    var memberId by TicketEntity.memberId
    var statusId by TicketEntity.statusId
    var seatId by TicketEntity.seatId
    var typeId by TicketEntity.typeId
}