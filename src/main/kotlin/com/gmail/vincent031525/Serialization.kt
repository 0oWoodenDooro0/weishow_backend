package com.gmail.vincent031525

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }
    routing {
        post("/test") {
            val movieList = listOf(
                Movie(1, "鋼鐵人", 180, "./鋼鐵人1.jpg"),
                Movie(2, "雷神索爾", 180, "./雷神索爾.jpg"),
                Movie(3, "鋼鐵人2", 180, "./鋼鐵人2.jpg"),
                Movie(4, "美國隊長", 180, "./美國隊長.jpg"),
                Movie(5, "復仇者聯盟", 180, "./復仇者聯盟.jpg"),
                Movie(6, "鋼鐵人3", 180, "./鋼鐵人3.jpg"),
            )
            call.respond(movieList)
        }
    }
}
