package com.gmail.vincent031525.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ManagerDto(
    val id: Int? = null,
    val username: String,
    val password: String
)
