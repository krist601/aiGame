package com.example.aigame.domain.repositories

import com.example.aigame.data.entities.requests.AnswerRequest
import com.example.aigame.data.entities.responses.QuestionResponse
import com.example.aigame.data.services.RetrofitMS
import retrofit2.http.Header
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val questionService: RetrofitMS
) {
    suspend fun getQuestion(userId: String, sessionId: String, answerRequest: AnswerRequest): QuestionResponse? {
        return try {
            questionService.getQuestion(userId, sessionId, answerRequest)
        } catch (e: Exception) {
            println("kris "+e.message)
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

