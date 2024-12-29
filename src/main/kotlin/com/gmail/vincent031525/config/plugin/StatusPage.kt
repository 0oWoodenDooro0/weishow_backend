package com.gmail.vincent031525.config.plugin

import com.gmail.vincent031525.domain.model.response.DataResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import kotlinx.serialization.SerializationException

fun Application.configureStatusPage() {
    install(StatusPages) {
        exception<RequestValidationException> { call, cause ->
            call.response.status(HttpStatusCode.BadRequest)
            call.respond(
                DataResponse<Nothing>(
                    HttpStatusCode.BadRequest.value,
                    cause.reasons.joinToString()
                )
            )
        }
        exception<BadRequestException> { call, cause ->
            call.response.status(HttpStatusCode.BadRequest)
            call.respond(
                DataResponse<Nothing>(
                    HttpStatusCode.BadRequest.value,
                    cause.message.orEmpty()
                )
            )
        }
        exception<SerializationException> { call, cause ->
            call.response.status(HttpStatusCode.BadRequest)
            call.respond(
                DataResponse<Nothing>(
                    HttpStatusCode.BadRequest.value,
                    cause.message.orEmpty()
                )
            )
        }
    }
}
