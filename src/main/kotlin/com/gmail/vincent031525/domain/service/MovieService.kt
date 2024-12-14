package com.gmail.vincent031525.domain.service

import com.gmail.vincent031525.domain.model.MovieDto
import com.gmail.vincent031525.domain.repository.MovieRepository

class MovieService(private val repository: MovieRepository) {
    suspend fun getAllMovies(): List<MovieDto> {
        return repository.getAllMovies()
    }
}