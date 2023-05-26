package com.example.aigame.data.services

import com.example.aigame.data.entities.QuestionResponse
import retrofit2.http.GET

interface RetrofitMS {
    @GET("germanServer/question")
    suspend fun getQuestion(): QuestionResponse
}