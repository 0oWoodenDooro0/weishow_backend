package com.gmail.vincent031525.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ScreenDto(val id: Int, val number: Int, val row: Int, val column: Int, val theaterId: Int)
