package com.example.aigame.data.data_sources.network

import com.example.aigame.data.entities.responses.OptionResponse
import com.example.aigame.data.services.RetrofitMS
import javax.inject.Inject

class QuestionNetworkDataSource @Inject constructor(
    private val questionService: RetrofitMS
) {
    suspend fun getChapter(chapterId: String): OptionResponse {
        return questionService.getChapter(chapterId, "cULuJB9amm39kEN3hFViaahdnmkAu3616KXiQZG8")
    }
}