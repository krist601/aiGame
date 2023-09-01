package com.example.aigame.domain.use_cases

import com.example.aigame.domain.entities.InterfaceResources
import com.example.aigame.domain.repositories.QuestionRepository
import javax.inject.Inject

class GetHomeMenuUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
) {
    fun hasSavedGame(): Boolean{
        return questionRepository.hasSavedGame()
    }
}