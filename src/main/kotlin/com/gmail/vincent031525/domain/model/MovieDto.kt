package com.gmail.vincent031525.domain.model


import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable


@Serializable
data class MovieDto(
    val id: Int? = null,
    val name: String,
    val releaseDate: LocalDate,
    val durationMin: Int,
    val description: String,
    val thumbnailPath: String,
    val contentRatingId: Int
)
