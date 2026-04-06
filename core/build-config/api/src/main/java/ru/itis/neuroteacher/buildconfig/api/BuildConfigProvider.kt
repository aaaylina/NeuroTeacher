package ru.itis.neuroteacher.buildconfig.api

interface BuildConfigProvider {
    fun getOpenRouterApiKey(): String
}