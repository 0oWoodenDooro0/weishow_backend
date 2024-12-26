package com.gmail.vincent031525

import com.gmail.vincent031525.config.plugin.*
import com.gmail.vincent031525.rest.setUpMovieRoutes
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureAuthentication()
    configureSerialization()
    configureKoin()
    configureRequestValidation()
    configureStatusPage()
    configureCors()
    setUpMovieRoutes()
}
