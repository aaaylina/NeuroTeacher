package ru.itis.neuroteacher.auth.presentation.login


sealed class LoginNavigationEvent {
    object NavigateToMain : LoginNavigationEvent()
}