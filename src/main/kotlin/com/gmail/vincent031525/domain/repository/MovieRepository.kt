package com.gmail.vincent031525.domain.repository

import com.gmail.vincent031525.domain.model.MovieDto

fun interface MovieRepository {
    suspend fun getAllMovies(): List<MovieDto>
}