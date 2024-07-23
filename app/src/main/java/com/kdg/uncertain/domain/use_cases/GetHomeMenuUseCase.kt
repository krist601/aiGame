package com.kdg.uncertain.domain.use_cases

import com.kdg.uncertain.domain.entities.InterfaceResources
import com.kdg.uncertain.domain.repositories.QuestionRepository
import javax.inject.Inject

class GetHomeMenuUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
) {
    fun hasSavedGame(): Boolean{
        return questionRepository.hasSavedGame()
    }
}