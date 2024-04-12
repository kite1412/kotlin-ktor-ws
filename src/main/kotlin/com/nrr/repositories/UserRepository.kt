package com.nrr.repositories

import com.nrr.models.User

class UserRepository private constructor(): InMemoryRepository<User, Int>() {

    companion object {
        private var INSTANCE: UserRepository? = null
        fun instance(): UserRepository {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = UserRepository()
                }
                return INSTANCE!!
            }
        }
    }

    private var lastId = 1

    override var data: MutableList<User> = mutableListOf()

    override fun getAll(): MutableList<User> {
        synchronized(data) {
            return data
        }
    }

    override fun insert(new: User): User {
        synchronized(data) {
            val u = new.apply { id = lastId }
            lastId++
            data.add(u)
            return u
        }
    }

    override fun getById(id: Int): User? {
        synchronized(data) {
            data.forEach {
                if (it.id == id) {
                    return it
                }
            }
        }
        return null
    }
}