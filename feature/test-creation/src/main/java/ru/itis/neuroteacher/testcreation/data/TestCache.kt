package ru.itis.neuroteacher.testcreation.data

import ru.itis.neuroteacher.testcreation.domain.model.Test
import java.util.UUID

object TestCache {
    private val store = mutableMapOf<String, Test>()

    fun save(test: Test): String {
        val id = UUID.randomUUID().toString()
        store[id] = test
        return id
    }

    fun get(id: String): Test? {
        val test = store[id]
        return test
    }

    fun clear(id: String) {
        store.remove(id)
    }
}