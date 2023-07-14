package com.example.aigame.domain.repositories

import com.example.aigame.data.entities.requests.ChatMessageRequest
import com.example.aigame.data.entities.requests.Message
import com.example.aigame.data.entities.responses.GameStorageResponse
import com.example.aigame.data.entities.responses.Level
import com.example.aigame.data.entities.responses.PayloadResponse
import com.example.aigame.data.entities.responses.Step
import com.example.aigame.data.services.OpenIaRetrofitMS
import com.example.aigame.data.services.RetrofitMS
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val questionService: RetrofitMS
) {
    suspend fun getGameStorage(): PayloadResponse<GameStorageResponse>? {
        return try {
            questionService.getGameStorage()
        } catch (e: Exception) {
            println("kris "+e.message)
            createMockGameStorageResponse()
        }
    }

    suspend fun getQuestion(userId: String, sessionId: String, chatMessageRequest: ChatMessageRequest): ChatMessageRequest? {
        return try {
            questionService.getQuestion("https://api.openai.com/", "Bearer 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", chatMessageRequest)
        } catch (e: Exception) {
            println("kris "+e.message)
            createMockQuestionResponse(userId)
        }
    }

    private fun createMockQuestionResponse(userId: String): ChatMessageRequest {
        return ChatMessageRequest(
            model = "gpt-3.5-turbo",
            messages = listOf(Message("user" ,"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolo  $userId"))
        )
    }
    private fun createMockGameStorageResponse(): PayloadResponse<GameStorageResponse> {
        val level1 = listOf(
            Step("German", "enemy","Creame una historia de maximo 5 lineas donde tenga 3 opciones para elegir de no mas de 5 palabras", "", 5)
        )
        val level2 = listOf(
            Step("Enemigo 2", "enemy", "construye un enemigo con maximo 5 preguntas y tenga 3 opciones", "", 5),
            Step("Enemigo 3", "enemy", "construye un enemigo con maximo 5 preguntas y tenga 3 opciones", "", 5),
            Step("Enemigo 4", "enemy", "construye un enemigo con maximo 5 preguntas y tenga 3 opciones", "", 5)
        )
        val level3 = listOf(
            Step("Enemigo 5", "enemy", "construye un enemigo con maximo 5 preguntas y tenga 3 opciones", "", 5),
            Step("Enemigo 6", "enemy", "construye un enemigo con maximo 5 preguntas y tenga 3 opciones", "", 5)
        )

        val levels = listOf(
            Level("Nivel 1", "", level1),
            Level("Nivel 2", "", level2),
            Level("Nivel 3", "", level3)
        )

        return PayloadResponse<GameStorageResponse>(200, GameStorageResponse("1.0.1", "1.0.1", "1.0.0", levels))
    }
}

