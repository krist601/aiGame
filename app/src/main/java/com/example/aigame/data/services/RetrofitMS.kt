package com.example.aigame.data.services

import com.example.aigame.data.entities.requests.ChatMessageRequest
import com.example.aigame.data.entities.responses.GameStorageResponse
import com.example.aigame.data.entities.responses.PayloadResponse
import com.example.aigame.data.entities.responses.QuestionResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Url

interface RetrofitMS {
    @GET("/story/story")
    suspend fun getGameStorage(): PayloadResponse<GameStorageResponse>

    @Headers("Content-Type: application/json")
    @POST("/v1/chat/completions")
    suspend fun getQuestion(@Url url: String, @Header("Authorization") authorization: String, @Body chatMessageRequest: ChatMessageRequest): ChatMessageRequest
}