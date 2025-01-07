package com.gmail.vincent031525.rest

import com.gmail.vincent031525.domain.model.*
import com.gmail.vincent031525.domain.model.request.*
import com.gmail.vincent031525.domain.model.response.DataResponse
import com.gmail.vincent031525.domain.repository.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.setBackendRoutes() {
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
                    call.respond(DataResponse(HttpStatusCode.Created.value, "success", id))
                }
            }
            route("/manager") {
                post {
                    val body = call.receive<ManagerDto>()
                    val id = managerRepository.addManager(body)
                    call.response.status(HttpStatusCode.Created)
                    call.respond(DataResponse(HttpStatusCode.Created.value, "success", id))
                }
                post("/login") {
                    val body = call.receive<ManagerLoginRequest>()
                    val response = managerRepository.login(body)
                    response.onSuccess { data ->
                        call.response.status(HttpStatusCode.OK)
                        call.respond(DataResponse(HttpStatusCode.OK.value, "success", data))
                    }.onFailure {
                        call.response.status(HttpStatusCode.BadRequest)
                        call.respond(DataResponse(HttpStatusCode.BadRequest.value, it.message.toString(), null))
                    }
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
                put("/{id}") {
                    val id = call.parameters["id"]
                    id?.let {
                        val body = call.receive<UpdateMemberRequest>()
                        val response = memberRepository.updateMember(it.toInt(), body)
                        response.onSuccess { id ->
                            call.response.status(HttpStatusCode.OK)
                            call.respond(DataResponse(HttpStatusCode.OK.value, "success", id))
                        }.onFailure {
                            call.response.status(HttpStatusCode.BadRequest)
                            call.respond(DataResponse(HttpStatusCode.BadRequest.value, it.message.toString(), null))
                        }
                    }
                }
                delete("/{id}") {
                    val id = call.parameters["id"]
                    id?.let {
                        val response = memberRepository.deleteMember(it.toInt())
                        response.onSuccess { id ->
                            call.response.status(HttpStatusCode.OK)
                            call.respond(DataResponse(HttpStatusCode.OK.value, "success", id))
                        }.onFailure { failed ->
                            call.response.status(HttpStatusCode.BadRequest)
                            call.respond(DataResponse(HttpStatusCode.BadRequest.value, failed.message.toString(), null))
                        }
                    }
                }
                post("/register") {
                    val body = call.receive<RegisterRequest>()
                    val response = memberRepository.register(body)
                    response.onSuccess { id ->
                        call.response.status(HttpStatusCode.Created)
                        call.respond(DataResponse(HttpStatusCode.Created.value, "success", id))
                    }.onFailure {
                        call.response.status(HttpStatusCode.BadRequest)
                        call.respond(DataResponse(HttpStatusCode.BadRequest.value, it.message.toString(), null))
                    }
                }
                post("/login") {
                    val body = call.receive<MemberLoginRequest>()
                    val response = memberRepository.login(body)
                    response.onSuccess { data ->
                        call.response.status(HttpStatusCode.OK)
                        call.respond(DataResponse(HttpStatusCode.OK.value, "success", data))
                    }.onFailure {
                        call.response.status(HttpStatusCode.BadRequest)
                        call.respond(DataResponse(HttpStatusCode.BadRequest.value, it.message.toString(), null))
                    }
                }
            }
            route("/movie") {
                get {
                    val response = movieRepository.getAllMovies()
                    response.onSuccess {
                        call.response.status(HttpStatusCode.OK)
                        call.respond(DataResponse(HttpStatusCode.OK.value, "success", it))
                    }.onFailure {
                        call.response.status(HttpStatusCode.BadRequest)
                        call.respond(DataResponse(HttpStatusCode.BadRequest.value, it.message.toString(), null))
                    }
                }
                post {
                    val body = call.receive<AddMovieRequest>()
                    val result = movieRepository.addMovie(body)
                    result.onSuccess { id ->
                        call.response.status(HttpStatusCode.Created)
                        call.respond(DataResponse(HttpStatusCode.Created.value, "success", id))
                    }.onFailure {
                        call.response.status(HttpStatusCode.BadRequest)
                        call.respond(DataResponse(HttpStatusCode.BadRequest.value, it.message.toString(), null))
                    }
                }
                put("/{id}") {
                    val id = call.parameters["id"]
                    val body = call.receive<UpdateMovieRequest>()
                    id?.let {
                        val response = movieRepository.updateMovie(it.toInt(), body)
                        response.onSuccess { id ->
                            call.response.status(HttpStatusCode.OK)
                            call.respond(DataResponse(HttpStatusCode.OK.value, "success", id))
                        }.onFailure { failed ->
                            call.response.status(HttpStatusCode.BadRequest)
                            call.respond(DataResponse(HttpStatusCode.BadRequest.value, failed.message.toString(), null))
                        }
                    }
                }
                delete("/{id}") {
                    val id = call.parameters["id"]
                    id?.let {
                        val response = movieRepository.deleteMovie(it.toInt())
                        response.onSuccess { id ->
                            call.response.status(HttpStatusCode.OK)
                            call.respond(DataResponse(HttpStatusCode.OK.value, "success", id))
                        }.onFailure { failed ->
                            call.response.status(HttpStatusCode.BadRequest)
                            call.respond(DataResponse(HttpStatusCode.BadRequest.value, failed.message.toString(), null))
                        }
                    }
                }
            }
            route("/screen") {
                get("/theater/{id}") {
                    val id = call.parameters["id"]
                    id?.let {
                        val response = screenRepository.getScreenByTheaterId(it.toInt())
                        response.onSuccess { id ->
                            call.response.status(HttpStatusCode.OK)
                            call.respond(DataResponse(HttpStatusCode.OK.value, "success", id))
                        }.onFailure { failed ->
                            call.response.status(HttpStatusCode.BadRequest)
                            call.respond(DataResponse(HttpStatusCode.BadRequest.value, failed.message.toString(), null))
                        }
                    }
                }
                post {
                    val body = call.receive<ScreenDto>()
                    val id = screenRepository.addScreen(body)
                    call.response.status(HttpStatusCode.Created)
                    call.respond(DataResponse(HttpStatusCode.Created.value, "success", id))
                }
            }
            route("/seat") {
                post("/session/{id}"){
                    val request = call.receive<GetSeatBySessionIdRequest>()
                    val id = call.parameters["id"]
                    id?.let { sessionId->
                        val response = seatRepository.getSeatBySessionId(sessionId.toInt(), request)
                        response.onSuccess { id ->
                            call.response.status(HttpStatusCode.OK)
                            call.respond(DataResponse(HttpStatusCode.OK.value, "success", id))
                        }.onFailure { failed ->
                            call.response.status(HttpStatusCode.BadRequest)
                            call.respond(DataResponse(HttpStatusCode.BadRequest.value, failed.message.toString(), null))
                        }
                    }
                }
                post {
                    val body = call.receive<SeatDto>()
                    val id = seatRepository.addSeat(body)
                    call.response.status(HttpStatusCode.Created)
                    call.respond(DataResponse(HttpStatusCode.Created.value, "succes", id))
                }
            }
            route("/session") {
                post {
                    val body = call.receive<AddSessionRequest>()
                    val response = sessionRepository.addSession(body)
                    response.onSuccess {
                        call.response.status(HttpStatusCode.Created)
                        call.respond(DataResponse(HttpStatusCode.Created.value, "success", it))
                    }.onFailure {
                        call.response.status(HttpStatusCode.BadRequest)
                        call.respond(DataResponse(HttpStatusCode.BadRequest.value, it.message.toString(), null))
                    }
                }
                put("/{id}") {
                    val id = call.parameters["id"]
                    val body = call.receive<UpdateSessionRequest>()
                    id?.let {
                        val response = sessionRepository.updateSession(it.toInt(), body)
                        response.onSuccess { id ->
                            call.response.status(HttpStatusCode.OK)
                            call.respond(DataResponse(HttpStatusCode.OK.value, "success", id))
                        }.onFailure { failed ->
                            call.response.status(HttpStatusCode.BadRequest)
                            call.respond(DataResponse(HttpStatusCode.BadRequest.value, failed.message.toString(), null))
                        }
                    }
                }
                delete("/{id}") {
                    val id = call.parameters["id"]
                    id?.let {
                        val response = sessionRepository.deleteSession(it.toInt())
                        response.onSuccess { id ->
                            call.response.status(HttpStatusCode.OK)
                            call.respond(DataResponse(HttpStatusCode.OK.value, "success", id))
                        }.onFailure { failed ->
                            call.response.status(HttpStatusCode.BadRequest)
                            call.respond(DataResponse(HttpStatusCode.BadRequest.value, failed.message.toString(), null))
                        }
                    }
                }
                get("/movie/{id}") {
                    val movieId = call.parameters["id"]
                    movieId?.let {
                        val response = sessionRepository.getSessionByMovieId(it.toInt())
                        call.response.status(HttpStatusCode.OK)
                        call.respond(DataResponse(HttpStatusCode.OK.value, "success", response))
                    }
                }
                post("/theater/movie") {
                    val request = call.receive<GetSessionByTheaterIdAndMovieIdRequest>()
                    val response =
                        sessionRepository.getSessionByTheaterIdAndMovieId(request)
                    response.onSuccess {
                        call.response.status(HttpStatusCode.OK)
                        call.respond(DataResponse(HttpStatusCode.OK.value, "success", it))
                    }.onFailure {
                        call.response.status(HttpStatusCode.BadRequest)
                        call.respond(DataResponse(HttpStatusCode.BadRequest.value, it.message.toString(), null))
                    }
                }
            }
            route("/theater") {
                get {
                    val response = theaterRepository.getTheater()
                    response.onSuccess {
                        call.response.status(HttpStatusCode.OK)
                        call.respond(DataResponse(HttpStatusCode.OK.value, "success", it))
                    }.onFailure {
                        call.response.status(HttpStatusCode.BadRequest)
                        call.respond(DataResponse(HttpStatusCode.BadRequest.value, it.message.toString(), null))
                    }
                }
                get("/manager/{id}") {
                    val id = call.parameters["id"]
                    id?.let {
                        val response = theaterRepository.getTheatersByManagerId(it.toInt())
                        response.onSuccess { data ->
                            call.response.status(HttpStatusCode.OK)
                            call.respond(DataResponse(HttpStatusCode.OK.value, "success", data))
                        }.onFailure { failed ->
                            call.response.status(HttpStatusCode.BadRequest)
                            call.respond(DataResponse(HttpStatusCode.BadRequest.value, failed.message.toString(), null))
                        }
                    }
                }
                post {
                    val body = call.receive<TheaterDto>()
                    val id = theaterRepository.addTheater(body)
                    call.response.status(HttpStatusCode.Created)
                    call.respond(DataResponse(HttpStatusCode.Created.value, "success", id))
                }
            }
            route("/ticket") {
                post {
                    val request = call.receive<AddTicketRequest>()
                    val response = ticketRepository.addTicket(request)
                    response.onSuccess {
                        call.response.status(HttpStatusCode.Created)
                        call.respond(DataResponse(HttpStatusCode.Created.value, "success", it))
                    }.onFailure {
                        call.response.status(HttpStatusCode.BadRequest)
                        call.respond(DataResponse(HttpStatusCode.BadRequest.value, it.message.toString(), null))
                    }
                }
                delete("/ticket/{id}") {
                    val id = call.parameters["id"]
                    id?.let {
                        val response = ticketRepository.deleteTicket(id.toInt())
                        response.onSuccess {
                            call.response.status(HttpStatusCode.OK)
                            call.respond(DataResponse(HttpStatusCode.OK.value, "success", it))
                        }.onFailure {
                            call.response.status(HttpStatusCode.BadRequest)
                            call.respond(DataResponse(HttpStatusCode.BadRequest.value, it.message.toString(), null))
                        }
                    }
                }
                get("/member/{id}") {
                    val id = call.parameters["id"]
                    id?.let {
                        val response = ticketRepository.getTicketsByMemberId(id.toInt())
                        response.onSuccess {
                            call.response.status(HttpStatusCode.OK)
                            call.respond(DataResponse(HttpStatusCode.OK.value, "success", it))
                        }.onFailure {
                            call.response.status(HttpStatusCode.BadRequest)
                            call.respond(DataResponse(HttpStatusCode.BadRequest.value, it.message.toString(), null))
                        }
                    }
                }
            }
        }
    }
}
