package ru.itis.neuroteacher.testcreation.data

import ru.itis.neuroteacher.testcreation.domain.model.Test
import java.util.UUID

class TestCache {
    private val store = mutableMapOf<String, Test>()

    fun save(test: Test): String = UUID.randomUUID().toString().also {
        store[it] = test
    }

    fun get(id: String): Test? = store[id]

    fun clear(id: String) {
        store.remove(id)
    }
}