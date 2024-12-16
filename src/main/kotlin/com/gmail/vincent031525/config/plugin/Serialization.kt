package com.gmail.vincent031525.config.plugin

import com.gmail.vincent031525.domain.model.DataResponse
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.MissingFieldException
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }
    install(StatusPages) {
        exception<MissingFieldException> { call, cause ->
            call.respond(DataResponse<Nothing>(HttpStatusCode.BadRequest.value, cause.message.toString()))
        }
    }
}