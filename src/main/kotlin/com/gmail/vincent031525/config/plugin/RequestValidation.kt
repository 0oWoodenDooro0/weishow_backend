package com.gmail.vincent031525.config.plugin

import com.gmail.vincent031525.domain.model.request.LoginRequest
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.configureRequestValidation() {
    install(RequestValidation) {
        validate<LoginRequest> { request ->
            if (request.email.isEmpty()) {
                ValidationResult.Invalid("A login email should not be empty.")
            } else if (request.password.isEmpty()) {
                ValidationResult.Invalid("A login password should not be empty.")
            } else {
                ValidationResult.Valid
            }
        }
    }
}