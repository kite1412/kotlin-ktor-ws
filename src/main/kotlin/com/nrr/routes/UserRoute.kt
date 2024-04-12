package com.nrr.routes

import com.nrr.models.dtos.CreateUser
import com.nrr.models.dtos.CreateUserResponse
import com.nrr.models.dtos.GetUser
import com.nrr.models.session.UserSession
import com.nrr.repositories.UserRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun Route.userRouting() {
    val repo = UserRepository.instance()
    route("/user") {
        get {
            val users = repo.getAll()
            if (users.isEmpty()) {
                call.respondText("No user registered")
                return@get
            }
            val converted = GetUser.fromUsers(users)
            call.respond(converted)
        }

        post {
            val body = call.receive<CreateUser>()
            val added = repo.insert(body.toUser())
            call.sessions.set(UserSession(added.id, added.username))
            val response = CreateUserResponse.generate(added)
            call.respond(response)
        }

        get("/{id?}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "No id provided",
                status = HttpStatusCode.NotFound
            )
            try {
                val user = repo.getById(id.toInt()) ?: return@get call.respondText(
                    "No user with id: $id",
                    status = HttpStatusCode.NotFound
                )
                call.respond(GetUser.fromUser(user))
            } catch (e: NumberFormatException) {
                call.respondText(
                    "Id must be of integer value",
                    status = HttpStatusCode.BadRequest
                )
            }
        }

        get("/@me") {
            val session = call.sessions.get<UserSession>() ?: return@get call.respondText(
                "You are not logged into an account",
                status = HttpStatusCode.BadRequest
            )
            call.respond(GetUser(session.id, session.username))
        }
    }
}