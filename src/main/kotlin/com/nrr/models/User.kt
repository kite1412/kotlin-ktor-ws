package com.nrr.models

data class User(
    var id: Int,
    val username: String,
    val password: String,
) {
    companion object {
        fun new(username: String, password: String): User {
            return User(id = -1, username = username, password = password)
        }
    }
}