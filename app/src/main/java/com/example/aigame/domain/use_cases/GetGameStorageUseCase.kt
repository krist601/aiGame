package com.example.aigame.domain.use_cases

import com.example.aigame.data.entities.responses.GameStorageResponse
import com.example.aigame.data.entities.responses.PayloadResponse
import com.example.aigame.domain.repositories.QuestionRepository
import javax.inject.Inject

class GetGameStorageUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
) {
    suspend operator fun invoke(): PayloadResponse<GameStorageResponse>? {
        return questionRepository.getGameStorage()
    }
}