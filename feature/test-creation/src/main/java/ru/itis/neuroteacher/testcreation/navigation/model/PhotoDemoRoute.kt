package ru.itis.neuroteacher.testcreation.navigation.model

import kotlinx.serialization.Serializable

@Serializable
data class PhotoDemoRoute(
    val imageUri: String,
    val recognizedText: String
)
