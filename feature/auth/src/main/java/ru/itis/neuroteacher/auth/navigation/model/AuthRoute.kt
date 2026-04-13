package ru.itis.neuroteacher.auth.navigation.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface AuthRoute {
    @Serializable
    data object Login : AuthRoute

    @Serializable
    data object Register : AuthRoute
}