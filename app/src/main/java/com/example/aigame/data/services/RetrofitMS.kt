package com.example.aigame.data.services

import com.example.aigame.data.entities.requests.AnswerRequest
import com.example.aigame.data.entities.responses.QuestionResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface RetrofitMS {
    @POST("messages")
    suspend fun getQuestion(@Header("userId") userId: String, @Header("sessionId") sessionId: String, @Body answerRequest: AnswerRequest): QuestionResponse
}