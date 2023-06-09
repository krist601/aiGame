package com.example.aigame.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aigame.data.entities.requests.AnswerRequest
import com.example.aigame.domain.use_cases.GetQuestionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val getQuestionUseCase: GetQuestionUseCase
) : ViewModel() {
    private val _question = MutableStateFlow("")
    val question: StateFlow<String> = _question

    private val _options = MutableStateFlow(emptyList<String>())
    val options: StateFlow<List<String>> = _options

    fun fetchQuestion() {
        viewModelScope.launch {
            val answerRequest = AnswerRequest("first commit")
            val questionResponse = getQuestionUseCase(userId = "userId", sessionId = "sessionId", answerRequest = answerRequest)
            _question.value = questionResponse?.question ?: ""
            _options.value = questionResponse?.options ?: emptyList()
        }
    }
}
