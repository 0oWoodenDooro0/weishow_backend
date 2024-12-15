package com.gmail.vincent031525.data.repository

import com.gmail.vincent031525.data.data_source.DataSource
import com.gmail.vincent031525.domain.model.MovieDto
import com.gmail.vincent031525.domain.repository.MovieRepository

class MovieRepositoryImpl(private val dataSource: DataSource) : MovieRepository {
    override suspend fun getAllMovies(): List<MovieDto> = dataSource.getAllMovies()
    override suspend fun addMovie(movieDto: MovieDto) {
        dataSource.addMovie(movieDto)
    }
}