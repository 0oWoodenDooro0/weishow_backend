package com.gmail.vincent031525.rest

import com.gmail.vincent031525.domain.model.MovieDto
import com.gmail.vincent031525.domain.model.PostResponse
import com.gmail.vincent031525.domain.service.MovieService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.setUpMovieRoutes() {
    val movieService by inject<MovieService>()
    routing {
        route("/movie") {
            post {
                val body = call.receive<MovieDto>()
                movieService.addMovie(body)
                call.respond(PostResponse(HttpStatusCode.Created.value, "Successfully added!"))
            }
        }
    }
}
