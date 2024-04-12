package com.nrr.models.dtos

import com.nrr.models.User
import kotlinx.serialization.Serializable

@Serializable
data class CreateUser(
    val username: String,
    val password: String
) {
    fun toUser(): User {
        return User.new(username = username, password = password)
    }
}
