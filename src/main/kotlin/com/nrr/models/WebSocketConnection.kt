package com.nrr.models

import com.nrr.models.session.UserSession
import io.ktor.websocket.*

data class WebSocketConnection(val session: DefaultWebSocketSession, val userSession: UserSession)
