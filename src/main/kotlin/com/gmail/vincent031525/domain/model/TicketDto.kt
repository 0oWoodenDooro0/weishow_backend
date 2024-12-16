package com.gmail.vincent031525.domain.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class TicketDto(
    val id: Int,
    val price: Int,
    val purchseTime: LocalDateTime,
    val sessionId: Int,
    val memberId: Int,
    val statusId: Int,
    val seatId: Int,
    val typeId: Int
)
