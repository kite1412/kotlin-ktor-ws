package com.nrr.plugins

import com.nrr.routes.userRouting
import com.nrr.routes.webSocketRouting
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        userRouting()
        webSocketRouting()
    }
}
