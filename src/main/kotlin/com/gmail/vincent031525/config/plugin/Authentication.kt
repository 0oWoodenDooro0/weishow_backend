package com.gmail.vincent031525.config.plugin

import io.ktor.server.application.*
import io.ktor.server.auth.*

fun Application.configureAuthentication() {
    install(Authentication) {
        bearer {
            realm = "Access to the '/' path"
            authenticate { tokenCredential ->
                if (tokenCredential.token == "a974f9b8a917f49dd75168ff85072644") {
                    UserIdPrincipal("apiKey")
                } else {
                    null
                }
            }
        }
    }
}