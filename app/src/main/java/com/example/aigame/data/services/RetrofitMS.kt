package com.example.aigame.data.services

import com.example.aigame.data.entities.responses.ChapterResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface RetrofitMS {
    @GET("/story/stories/seasons/S1/chapters/{id}")
    suspend fun getChapter(@Path("id") id: String, @Header("x-api-key") secret: String): ChapterResponse
}