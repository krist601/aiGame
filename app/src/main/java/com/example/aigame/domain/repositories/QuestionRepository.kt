package com.example.aigame.domain.repositories

import com.example.aigame.data.entities.QuestionResponse
import com.example.aigame.data.services.RetrofitMS
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val questionService: RetrofitMS
) {
    suspend fun getQuestion(): QuestionResponse? {
        return try {
            questionService.getQuestion()
        } catch (e: Exception) {
            createMockQuestionResponse()
        }
    }

    private fun createMockQuestionResponse(): QuestionResponse {
        return QuestionResponse(
            question = "This is a mock question",
            options = listOf("Option 1", "Option 2", "Option 3")
        )
    }
}

