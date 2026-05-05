package ru.itis.neuroteacher.common.model

data class AppSettings(
    val theme: ThemeOption = ThemeOption.SYSTEM,
    val language: LanguageOption = LanguageOption.RUSSIAN
)