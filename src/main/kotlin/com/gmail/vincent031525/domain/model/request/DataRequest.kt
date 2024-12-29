package com.gmail.vincent031525.domain.model.request

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class MemberLoginRequest(val email: String, val password: String)

@Serializable
data class RegisterRequest(val name: String, val email: String, val password: String)

@Serializable
data class UpdateMemberRequest(val name: String? = null, val email: String? = null, val password: String? = null)

@Serializable
data class AddMovieRequest(
    val name: String,
    val releaseDate: LocalDate,
    val durationMin: Int,
    val description: String,
    val thumbnailPath: String,
    val contentRatingId: Int
)

@Serializable
data class UpdateMovieRequest(
    val name: String? = null,
    val releaseDate: LocalDate? = null,
    val durationMin: Int? = null,
    val description: String? = null,
    val thumbnailPath: String? = null,
    val contentRatingId: Int? = null
)

@Serializable
data class ManagerLoginRequest(val username: String, val password: String)
