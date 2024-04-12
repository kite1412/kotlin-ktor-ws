package com.nrr.repositories

abstract class InMemoryRepository<T, ID> {

    abstract var data: MutableList<T>

    abstract fun getAll(): MutableList<T>

    abstract fun getById(id: ID): T?

    abstract fun insert(new: T): T
}