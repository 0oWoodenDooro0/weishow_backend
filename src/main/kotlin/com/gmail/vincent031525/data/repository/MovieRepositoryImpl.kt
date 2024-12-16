package com.gmail.vincent031525.data.repository

import com.gmail.vincent031525.data.data_source.dao.MovieDao
import com.gmail.vincent031525.data.data_source.entity.ContentRatingEntity
import com.gmail.vincent031525.domain.model.MovieDto
import com.gmail.vincent031525.domain.repository.MovieRepository
import org.jetbrains.exposed.dao.id.EntityID

class MovieRepositoryImpl : MovieRepository {
    override suspend fun getAllMovies(): List<MovieDto> = query {
        MovieDao.all().map {
            MovieDto(
                it.id.value,
                it.name,
                it.releaseTime,
                it.durationMin,
                it.description,
                it.thumbnailPath,
                it.contentRatingId.value
            )
        }
    }

    override suspend fun addMovie(movieDto: MovieDto): Int = query {
        MovieDao.new {
            name = movieDto.name
            releaseTime = movieDto.releaseDate
            durationMin = movieDto.durationMin
            description = movieDto.description
            thumbnailPath = movieDto.thumbnailPath
            contentRatingId = EntityID(movieDto.contentRatingId, ContentRatingEntity)
        }.id.value
    }

    override suspend fun updateMovie(id: Int, movieDto: MovieDto) {
        MovieDao.findById(id)?.apply {
            name = movieDto.name
            releaseTime = movieDto.releaseDate
            durationMin = movieDto.durationMin
            description = movieDto.description
            thumbnailPath = movieDto.thumbnailPath
            contentRatingId = EntityID(movieDto.contentRatingId, ContentRatingEntity)
        }
    }
}