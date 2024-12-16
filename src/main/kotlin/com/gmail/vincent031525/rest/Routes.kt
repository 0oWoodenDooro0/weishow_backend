package com.gmail.vincent031525.rest

import com.gmail.vincent031525.domain.model.*
import com.gmail.vincent031525.domain.repository.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.setUpMovieRoutes() {
    val memberRepository by inject<MemberRepository>()
    val movieRepository by inject<MovieRepository>()
    val screenRepository by inject<ScreenRepository>()
    val seatRepository by inject<SeatRepository>()
    val sessionRepository by inject<SessionRepository>()
    val theaterRepository by inject<TheaterRepository>()
    val ticketRepository by inject<TicketRepository>()
    routing {
        route("/member") {
            post {
                val body = call.receive<MemberDto>()
                val id = memberRepository.addMember(body)
                call.response.status(HttpStatusCode.Created)
                call.respond(DataResponse(HttpStatusCode.Created.value, "Successfully added!", id))
            }
        }
        route("/movie") {
            get {
                call.response.status(HttpStatusCode.OK)
                call.respond(DataResponse(HttpStatusCode.OK.value, "success", movieRepository.getAllMovies()))
            }
            post {
                val body = call.receive<MovieDto>()
                val id = movieRepository.addMovie(body)
                call.response.status(HttpStatusCode.Created)
                call.respond(DataResponse(HttpStatusCode.Created.value, "Successfully added!", id))
            }
        }
        route("/screen") {
            post {
                val body = call.receive<ScreenDto>()
                val id = screenRepository.addScreen(body)
                call.response.status(HttpStatusCode.Created)
                call.respond(DataResponse(HttpStatusCode.Created.value, "Successfully added!", id))
            }
        }
        route("/seat") {
            post {
                val body = call.receive<SeatDto>()
                val id = seatRepository.addSeat(body)
                call.response.status(HttpStatusCode.Created)
                call.respond(DataResponse(HttpStatusCode.Created.value, "Successfully added!", id))
            }
        }
        route("/session") {
            post {
                val body = call.receive<SessionDto>()
                val id = sessionRepository.addSession(body)
                call.response.status(HttpStatusCode.Created)
                call.respond(DataResponse(HttpStatusCode.Created.value, "Successfully added!", id))
            }
        }
        route("/theater") {
            post {
                val body = call.receive<TheaterDto>()
                val id = theaterRepository.addTheater(body)
                call.response.status(HttpStatusCode.Created)
                call.respond(DataResponse(HttpStatusCode.Created.value, "Successfully added!", id))
            }
        }
        route("/ticket") {
            post {
                val body = call.receive<TicketDto>()
                val id = ticketRepository.addTicket(body)
                call.response.status(HttpStatusCode.Created)
                call.respond(DataResponse(HttpStatusCode.Created.value, "Successfully added!", id))
            }
        }
    }
}
