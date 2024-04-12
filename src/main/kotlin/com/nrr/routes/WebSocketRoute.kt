package com.nrr.routes

import com.nrr.models.WebSocketConnection
import com.nrr.models.session.UserSession
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import java.util.Collections

fun Routing.webSocketRouting() {
    val connections = Collections.synchronizedList<WebSocketConnection>(mutableListOf())
    webSocket("/messaging") {
        lateinit var connection: WebSocketConnection
        var conError: Exception? = null
        try {
            // TODO: based on latest logged in account
            val session = call.sessions.get<UserSession>() ?: UserSession(1, "Schierke")
            connection = WebSocketConnection(this, session)
            connections += connection
            send("Connected to websocket")
            for (frame: Frame in incoming) {
                if (frame is Frame.Text) {
                    val formatted = "${connection.userSession.username}: ${frame.readText()}"
                    connections.forEach {
                        it.session.send(formatted)
                    }
                }
            }
        } catch (e: IllegalStateException) {
            conError = e
            call.respondText(
                "Internal server error",
                status = HttpStatusCode.InternalServerError
            )
        } finally {
            if (conError == null) {
                connections -= connection
            }
        }
    }
}