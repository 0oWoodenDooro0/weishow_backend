package com.gmail.vincent031525.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SeatDto(val id: Int, val row: Int, val column: Int, val number: String)
