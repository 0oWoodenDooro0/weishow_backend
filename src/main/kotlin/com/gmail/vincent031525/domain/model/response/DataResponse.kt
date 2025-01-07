package com.gmail.vincent031525.domain.model.response

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.serialization.Serializable

@Serializable
data class DataResponse<T>(val code: Int, val message: String, val data: T? = null)

@Serializable
data class LoginResponse(
    val id: Int? = null,
    val name: String,
    val email: String,
)

@Serializable
data class TicketResponse(
    val id: Int,
    val date: LocalDate,
    val theaterName: String,
    val screenNumber: Int,
    val startTime: LocalTime,
    val movieName: String,
    val price: Int,
    val seatNumber: String
)