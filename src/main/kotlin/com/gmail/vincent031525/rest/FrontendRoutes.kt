package com.gmail.vincent031525.rest

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.setFrontendRoutes() {
    routing {
        staticResources("/", "templates/home")
        staticResources("/admin", "templates/admin")
    }
}
