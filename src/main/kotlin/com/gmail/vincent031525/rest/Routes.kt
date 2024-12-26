package com.gmail.vincent031525.rest

import com.gmail.vincent031525.domain.model.*
import com.gmail.vincent031525.domain.model.request.LoginRequest
import com.gmail.vincent031525.domain.model.response.DataResponse
import com.gmail.vincent031525.domain.repository.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.setUpMovieRoutes() {
    val managementRepository by inject<ManagementRepository>()
    val managerRepository by inject<ManagerRepository>()
    val memberRepository by inject<MemberRepository>()
    val movieRepository by inject<MovieRepository>()
    val screenRepository by inject<ScreenRepository>()
    val seatRepository by inject<SeatRepository>()
    val sessionRepository by inject<SessionRepository>()
    val theaterRepository by inject<TheaterRepository>()
    val ticketRepository by inject<TicketRepository>()

    routing {
        authenticate("auth-bearer") {
            route("/management") {
                post {
                    val body = call.receive<ManagementDto>()
                    val id = managementRepository.addManagement(body)
                    call.response.status(HttpStatusCode.Created)
                    call.respond(DataResponse(HttpStatusCode.Created.value, "Successfully added!", id))
                }
            }
            route("/manager") {
                post {
                    val body = call.receive<ManagerDto>()
                    val id = managerRepository.addManager(body)
                    call.response.status(HttpStatusCode.Created)
                    call.respond(DataResponse(HttpStatusCode.Created.value, "Successfully added!", id))
                }
            }
            route("/member") {
                get {
                    val result = memberRepository.getAllMembers()
                    result.onSuccess {
                        call.response.status(HttpStatusCode.OK)
                        call.respond(DataResponse(HttpStatusCode.OK.value, "success", it))
                    }
                }
                post {
                    val body = call.receive<MemberDto>()
                    val id = memberRepository.addMember(body)
                    call.response.status(HttpStatusCode.Created)
                    call.respond(DataResponse(HttpStatusCode.Created.value, "Successfully added!", id))
                }
                put {
                    val body = call.receive<MemberDto>()
                    body.id?.let {
                        memberRepository.updateMember(it, body)
                        call.response.status(HttpStatusCode.OK)
                        call.respond(DataResponse(HttpStatusCode.OK.value, "Successfully added!", it))
                        return@put
                    }
                    call.response.status(HttpStatusCode.BadRequest)
                    call.respond(DataResponse(HttpStatusCode.BadRequest.value, "Missing field id", null))
                }
                post("/login") {
                    val body = call.receive<LoginRequest>()
                    val response = memberRepository.getMemberByEmailAndPassword(body)
                    response.onSuccess { data ->
                        call.response.status(HttpStatusCode.OK)
                        call.respond(DataResponse(HttpStatusCode.OK.value, "Successfully logged in!", data))
                    }.onFailure {
                        call.response.status(HttpStatusCode.BadRequest)
                        call.respond(DataResponse(HttpStatusCode.BadRequest.value, "Wrong email or password!", null))
                    }
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
                put {
                    val body = call.receive<MovieDto>()
                    body.id?.let {
                        movieRepository.updateMovie(it, body)
                        call.response.status(HttpStatusCode.OK)
                        call.respond(DataResponse(HttpStatusCode.OK.value, "Successfully added!", it))
                        return@put
                    }
                    call.response.status(HttpStatusCode.BadRequest)
                    call.respond(DataResponse(HttpStatusCode.BadRequest.value, "Missing Field Id", null))
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
                put {
                    val body = call.receive<TicketDto>()
                    body.id?.let {
                        ticketRepository.updateTicket(it, body)
                        call.response.status(HttpStatusCode.OK)
                        call.respond(DataResponse(HttpStatusCode.OK.value, "Successfully updated!", it))
                        return@put
                    }
                    call.response.status(HttpStatusCode.BadRequest)
                    call.respond(DataResponse(HttpStatusCode.BadRequest.value, "Missing Field Id", null))
                }
                get("/member/{id}") {
                    val id = call.parameters["id"]
                    id?.let {
                        val tickets = ticketRepository.getTicketsByMemberId(id.toInt())
                        call.response.status(HttpStatusCode.Created)
                        call.respond(DataResponse(HttpStatusCode.Created.value, "Successfully logged in!", tickets))
                    }
                    call.response.status(HttpStatusCode.BadRequest)
                    call.respond(DataResponse(HttpStatusCode.BadRequest.value, "Missing Field Id", null))
                }
            }
        }
    }
}
