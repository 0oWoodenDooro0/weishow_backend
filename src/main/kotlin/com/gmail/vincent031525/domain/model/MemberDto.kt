package com.gmail.vincent031525.domain.model

data class MemberDto(
    val id: Int? = null,
    val name: String,
    val email: String,
    val password: String,
    val gender: String
)
