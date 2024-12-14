package com.gmail.vincent031525.rest

import com.gmail.vincent031525.domain.service.MovieService
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.setUpMovieRoutes() {
    val movieService by inject<MovieService>()
    routing {
        get("/test") {
            val movieList = movieService.getAllMovies()
            call.respond(movieList)
        }
    }
}
