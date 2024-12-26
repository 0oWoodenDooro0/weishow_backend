package com.gmail.vincent031525.domain.model.response

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val id: Int? = null,
    val name: String,
    val email: String,
)
