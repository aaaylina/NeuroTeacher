package ru.itis.neuroteacher.network.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ru.itis.neuroteacher.network.model.request.OpenRouterRequest
import ru.itis.neuroteacher.network.model.response.OpenRouterResponse

interface OpenRouterApi {

    @POST("chat/completions")
    suspend fun generateTest(
        @Body request: OpenRouterRequest
    ): Response<OpenRouterResponse>
}