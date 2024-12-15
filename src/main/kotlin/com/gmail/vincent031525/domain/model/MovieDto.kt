package com.gmail.vincent031525.domain.model


import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable


@Serializable
data class MovieDto(
    val id: Int? = null,
    val name: String,
    val releaseDate: LocalDate,
    val director: String,
    val actors: List<String>,
    val length: Int,
    val description: String,
    val thumbnailPath: String,
    val contentRatingId: Int
)
