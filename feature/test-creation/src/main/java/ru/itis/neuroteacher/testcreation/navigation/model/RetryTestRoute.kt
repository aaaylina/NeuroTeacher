package ru.itis.neuroteacher.testcreation.navigation.model

import kotlinx.serialization.Serializable

@Serializable
data class RetryTestRoute(
    val testId: Long
)