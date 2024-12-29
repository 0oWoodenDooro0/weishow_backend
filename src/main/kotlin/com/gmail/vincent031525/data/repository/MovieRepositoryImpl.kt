package com.gmail.vincent031525.data.repository

import com.gmail.vincent031525.data.data_source.dao.MovieDao
import com.gmail.vincent031525.data.data_source.entity.ContentRatingEntity
import com.gmail.vincent031525.data.data_source.entity.MovieEntity
import com.gmail.vincent031525.domain.model.MovieDto
import com.gmail.vincent031525.domain.model.request.AddMovieRequest
import com.gmail.vincent031525.domain.model.request.UpdateMovieRequest
import com.gmail.vincent031525.domain.repository.MovieRepository
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class MovieRepositoryImpl : MovieRepository {
    override suspend fun getAllMovies(): Result<List<MovieDto>> = try {
        query {
            val movies = MovieDao.find(MovieEntity.removed eq false).map {
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
            Result.success(movies)
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun addMovie(addMovieRequest: AddMovieRequest): Result<Int> = try {
        query {
            val id = MovieDao.new {
                name = addMovieRequest.name
                releaseTime = addMovieRequest.releaseDate
                durationMin = addMovieRequest.durationMin
                description = addMovieRequest.description
                thumbnailPath = addMovieRequest.thumbnailPath
                contentRatingId = EntityID(addMovieRequest.contentRatingId, ContentRatingEntity)
            }.id.value
            Result.success(id)
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun updateMovie(id: Int, updateMovieRequest: UpdateMovieRequest): Result<Int> = try {
        query {
            MovieDao.findById(id)?.apply {
                updateMovieRequest.name?.let { name = it }
                updateMovieRequest.releaseDate?.let { releaseTime = it }
                updateMovieRequest.durationMin?.let { durationMin = it }
                updateMovieRequest.description?.let { description = it }
                updateMovieRequest.thumbnailPath?.let { thumbnailPath = it }
                updateMovieRequest.contentRatingId?.let { contentRatingId = EntityID(it, ContentRatingEntity) }
            }
            Result.success(id)
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun deleteMovie(id: Int): Result<Int> = try {
        query {
            MovieDao.findById(id)?.apply {
                removed = true
            }
            Result.success(id)
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}