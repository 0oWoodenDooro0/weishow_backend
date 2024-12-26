package com.gmail.vincent031525.domain.model.request

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(val email: String, val password: String)
