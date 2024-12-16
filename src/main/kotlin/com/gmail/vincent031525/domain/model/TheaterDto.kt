package com.gmail.vincent031525.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class TheaterDto(
    val id: Int? = null,
    val name: String,
    val address: String,
    val phoneNumber: String,
    val description: String
)
