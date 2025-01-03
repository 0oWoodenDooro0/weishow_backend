package com.gmail.vincent031525.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MemberDto(
    val id: Int? = null,
    val name: String,
    val email: String,
    val password: String
)
