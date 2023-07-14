package com.example.aigame.domain.use_cases

import com.example.aigame.data.entities.requests.AnswerRequest
import com.example.aigame.data.entities.requests.ChatMessageRequest
import com.example.aigame.data.entities.responses.QuestionResponse
import com.example.aigame.domain.repositories.QuestionRepository
import javax.inject.Inject

class GetQuestionUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
) {
    suspend operator fun invoke(userId: String, sessionId: String, chatMessageRequest: ChatMessageRequest): ChatMessageRequest? {
        return questionRepository.getQuestion(userId, sessionId, chatMessageRequest)
    }
}
