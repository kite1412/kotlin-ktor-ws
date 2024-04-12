package com.nrr.models.dtos

import com.nrr.models.User
import kotlinx.serialization.Serializable

@Serializable
data class GetUser(
    val id: Int,
    val username: String
) {
    companion object {
        fun fromUser(user: User): GetUser {
            return GetUser(id = user.id, username = user.username)
        }

        fun fromUsers(users: List<User>): List<GetUser> {
            val u = mutableListOf<GetUser>()
            users.forEach {
                u.add(GetUser(id = it.id, username = it.username))
            }
            return u
        }
    }
}
