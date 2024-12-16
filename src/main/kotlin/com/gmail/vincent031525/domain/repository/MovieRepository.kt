package com.gmail.vincent031525.domain.repository

import com.gmail.vincent031525.domain.model.MovieDto

interface MovieRepository {
    suspend fun getAllMovies(): List<MovieDto>
    suspend fun addMovie(movieDto: MovieDto): Int
}