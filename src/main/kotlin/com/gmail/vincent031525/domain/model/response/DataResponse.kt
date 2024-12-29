package com.gmail.vincent031525.domain.model.response

import kotlinx.serialization.Serializable

@Serializable
data class DataResponse<T>(val code: Int, val message: String, val data: T? = null)

@Serializable
data class LoginResponse(
    val id: Int? = null,
    val name: String,
    val email: String,
)