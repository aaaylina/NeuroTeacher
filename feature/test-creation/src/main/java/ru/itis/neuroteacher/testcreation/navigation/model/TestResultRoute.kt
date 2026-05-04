package ru.itis.neuroteacher.testcreation.navigation.model

import kotlinx.serialization.Serializable

@Serializable
data class TestResultRoute(
    val testId: Long,
    val resultId: Long
)