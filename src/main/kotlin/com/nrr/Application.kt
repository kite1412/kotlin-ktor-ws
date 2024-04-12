package com.nrr

import com.nrr.plugins.*
import io.ktor.server.application.*
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module() {
    configureSockets()
    configureSerialization()
    configureRouting()
    configureSessions()
}