package com.example.aigame.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aigame.domain.entities.ChapterEntity
import com.example.aigame.domain.entities.InterfaceResources
import com.example.aigame.domain.entities.Option
import com.example.aigame.domain.use_cases.GetChapterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val getChapterUseCase: GetChapterUseCase
) : ViewModel() {

    private val _chapterData = MutableStateFlow(ChapterEntity())
    val chapterData: StateFlow<ChapterEntity> = _chapterData

    private val _optionData = MutableStateFlow(Option())
    val optionData: StateFlow<Option> = _optionData

    private val _viewStateFlow = MutableStateFlow<ViewStates>(ViewStates.Loading)
    val viewStateFlow: StateFlow<ViewStates> = _viewStateFlow

    fun getChapter(chapterId: String){
        _viewStateFlow.value = ViewStates.Loading
        viewModelScope.launch {
            val chapterEntity = getChapterUseCase(chapterId)
            if (chapterEntity != null) {
                _chapterData.value = chapterEntity
                chapterEntity.branch?.let { _optionData.value = it }
                _viewStateFlow.value = ViewStates.Questions
            }
        }
    }
    fun setNewQuestion(option: Option){
        _viewStateFlow.value = ViewStates.Loading
        getChapterUseCase.setSavedGame(option)
        getChapterUseCase.setInterfaceResources(_chapterData.value.interfaceResources)
        viewModelScope.launch {
            delay(3000)
            _optionData.value = option
            _viewStateFlow.value =
                if (!option.next_chapter_id.isNullOrBlank()) ViewStates.NextChapter
                else if (!option.options.isNullOrEmpty()) ViewStates.Questions
                else {
                    getChapterUseCase.deleteSavedGame()
                    _chapterData.value = ChapterEntity(interfaceResources = option.interfaceResources)
                    ViewStates.DeadEnd
                }

        }
    }
    fun getSavedGame() {
        viewModelScope.launch {
            val option = getChapterUseCase.getSavedGame()
            val interfaceResources = getChapterUseCase.getInterfaceResources()
            _chapterData.update {
                it.copy(interfaceResources = interfaceResources, branch = option)
            }
            option?.let { _optionData.value = it }
            _viewStateFlow.value = ViewStates.Questions
        }
    }

    fun getAnswerState(){
        viewModelScope.launch {
            _viewStateFlow.value = ViewStates.Answers
        }
    }
}

sealed class ViewStates {
    object Loading : ViewStates()
    object Questions : ViewStates()
    object Answers : ViewStates()
    object DeadEnd : ViewStates()
    object NextChapter : ViewStates()
}