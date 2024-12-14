package com.gmail.vincent031525.data.repository

import com.gmail.vincent031525.data.data_source.LocalDataSource
import com.gmail.vincent031525.domain.model.MovieDto
import com.gmail.vincent031525.domain.repository.MovieRepository

class MovieRepositoryImpl(private val localDataSource: LocalDataSource) : MovieRepository {
    override suspend fun getAllMovies(): List<MovieDto> = localDataSource.getAllMovies()
}