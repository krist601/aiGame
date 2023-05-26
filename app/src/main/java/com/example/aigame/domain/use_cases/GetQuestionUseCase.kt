package com.example.aigame.domain.use_cases

import com.example.aigame.data.entities.QuestionResponse
import com.example.aigame.domain.repositories.QuestionRepository
import javax.inject.Inject

class GetQuestionUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
) {
    suspend operator fun invoke(): QuestionResponse? {
        return questionRepository.getQuestion()
    }
}
