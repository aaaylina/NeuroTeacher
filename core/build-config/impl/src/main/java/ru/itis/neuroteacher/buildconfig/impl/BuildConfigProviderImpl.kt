package ru.itis.neuroteacher.buildconfig.impl

import ru.itis.neuroteacher.buildconfig.api.BuildConfigProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BuildConfigProviderImpl @Inject constructor() : BuildConfigProvider{

    override fun getOpenRouterApiKey(): String {
        return BuildConfig.OPENROUTER_API_KEY
    }
}