package ru.itis.neuroteacher.network.model.request

data class OpenRouterRequest(
    val model: String = "",
    val messages: List<Message>,
    val temperature: Double = 0.7
)

data class Message(
    val content: String
)