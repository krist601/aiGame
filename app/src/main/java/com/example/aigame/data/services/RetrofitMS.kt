package com.example.aigame.data.services

import com.example.aigame.data.entities.responses.ChapterResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitMS {
    @GET("/chapter/{id}")
    suspend fun getChapter(@Path("id") id: String): ChapterResponse
}