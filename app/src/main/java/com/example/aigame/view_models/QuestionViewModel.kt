package com.example.aigame.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aigame.data.entities.requests.AnswerRequest
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

    fun getQuestion(answer: String) {
        viewModelScope.launch {
            currentAnswer++
            val answerRequest = AnswerRequest(answer)
            val questionResponse = getQuestionUseCase(userId = currentAnswer.toString(), sessionId = "sessionId", answerRequest = answerRequest)
            if (questionResponse != null) {
                _question.value = questionResponse
            }
        }
    }
    fun startLevel() {
        viewModelScope.launch {
            val answerRequest = AnswerRequest(gameData.value.levels?.get(currentLevel)?.enemies?.get(currentEnemy)?.initialPrompt ?: "")
            val questionResponse = getQuestionUseCase(userId = "userId", sessionId = "sessionId", answerRequest = answerRequest)
            if (questionResponse != null) {
                _question.value = questionResponse
            }
        }
    }
    fun getGameStorage(){
        viewModelScope.launch {
            val gameStorageResponse = getGameStorageUseCase()
            if (gameStorageResponse != null) {
                _gameData.value = gameStorageResponse
            }
        }
    }
}
