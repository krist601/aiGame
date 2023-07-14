package com.example.aigame.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aigame.data.entities.requests.ChatMessageRequest
import com.example.aigame.data.entities.requests.Message
import com.example.aigame.data.entities.responses.GameStorageResponse
import com.example.aigame.data.entities.responses.QuestionResponse
import com.example.aigame.domain.use_cases.GetGameStorageUseCase
import com.example.aigame.domain.use_cases.GetQuestionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val getQuestionUseCase: GetQuestionUseCase,
    private val getGameStorageUseCase: GetGameStorageUseCase
) : ViewModel() {
    private val _question = MutableStateFlow(QuestionResponse())
    val question: StateFlow<QuestionResponse> = _question

    private val _gameData = MutableStateFlow(GameStorageResponse())
    val gameData: StateFlow<GameStorageResponse> = _gameData

    var currentLevel = 0
    var currentEnemy = 0
    var currentAnswer = 0
    val stepHistory = ArrayList<Message>()

    fun getQuestion(answer: String) {
        viewModelScope.launch {
            currentAnswer++
            stepHistory.add(Message("user", answer))
            val chatMessageRequest = ChatMessageRequest("gpt-3.5-turbo", stepHistory)
            val questionResponse = getQuestionUseCase(userId = currentAnswer.toString(), sessionId = "sessionId", chatMessageRequest = chatMessageRequest)
            if (questionResponse != null) {
                stepHistory.add(Message("system", questionResponse.messages.last().content))
                _question.value = QuestionResponse(questionResponse.messages.last().content, listOf("Opcion 1","Opcion 2","Opcion 3"))
            }
        }
    }
    fun startLevel() {
        viewModelScope.launch {
            stepHistory.clear()
            getQuestion(_gameData.value.levels?.get(currentLevel)?.step?.get(currentEnemy)?.initialPrompt ?: "")
        }
    }
    fun getGameStorage(){
        viewModelScope.launch {
            val gameStorageResponse = getGameStorageUseCase()
            if (gameStorageResponse != null) {
                _gameData.value = gameStorageResponse.data
            }
        }
    }
}
