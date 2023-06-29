package com.example.aigame.domain.repositories

import com.example.aigame.data.entities.requests.AnswerRequest
import com.example.aigame.data.entities.responses.Enemy
import com.example.aigame.data.entities.responses.GameStorageResponse
import com.example.aigame.data.entities.responses.Level
import com.example.aigame.data.entities.responses.QuestionResponse
import com.example.aigame.data.services.RetrofitMS
import retrofit2.http.Header
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val questionService: RetrofitMS
) {
    suspend fun getGameStorage(): GameStorageResponse? {
        return try {
            questionService.getGameStorage()
        } catch (e: Exception) {
            println("kris "+e.message)
            createMockGameStorageResponse()
        }
    }

    suspend fun getQuestion(userId: String, sessionId: String, answerRequest: AnswerRequest): QuestionResponse? {
        return try {
            questionService.getQuestion(userId, sessionId, answerRequest)
        } catch (e: Exception) {
            println("kris "+e.message)
            createMockQuestionResponse(userId)
        }
    }

    private fun createMockQuestionResponse(userId: String): QuestionResponse {
        return QuestionResponse(
            question = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu  $userId",
            options = listOf("Option$userId 1", "Option$userId 2", "Option$userId 3")
        )
    }
    private fun createMockGameStorageResponse(): GameStorageResponse {
        val level1 = listOf(
            Enemy("German", "Creame una historia de maximo 5 lineas donde tenga 3 opciones para elegir de no mas de 5 palabras")
        )
        val level2 = listOf(
            Enemy("Enemigo 2", "construye un enemigo con maximo 5 preguntas y tenga 3 opciones"),
            Enemy("Enemigo 3", "construye un enemigo con maximo 5 preguntas y tenga 3 opciones"),
            Enemy("Enemigo 4", "construye un enemigo con maximo 5 preguntas y tenga 3 opciones")
        )
        val level3 = listOf(
            Enemy("Enemigo 5", "construye un enemigo con maximo 5 preguntas y tenga 3 opciones"),
            Enemy("Enemigo 6", "construye un enemigo con maximo 5 preguntas y tenga 3 opciones")
        )

        val levels = listOf(
            Level("Nivel 1", level1),
            Level("Nivel 2", level2),
            Level("Nivel 3", level3)
        )

        return GameStorageResponse("1.0.1", "1.0.1", "1.0.0", levels)
    }
}

