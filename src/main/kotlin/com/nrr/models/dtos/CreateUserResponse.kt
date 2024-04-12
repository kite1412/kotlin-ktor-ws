package com.nrr.models.dtos

import com.nrr.models.User
import kotlinx.serialization.Serializable

@Serializable
data class CreateUserResponse(
    val message: String,
    val createdUser: GetUser
) {
    companion object {
        fun generate(user: User): CreateUserResponse {
            return CreateUserResponse("Successfully registered", GetUser.fromUser(user))
        }
    }
}