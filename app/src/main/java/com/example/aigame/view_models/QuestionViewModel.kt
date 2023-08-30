package com.example.aigame.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aigame.data.util.SharedPreferencesHelper
import com.example.aigame.domain.entities.ChapterEntity
import com.example.aigame.domain.entities.Option
import com.example.aigame.domain.use_cases.GetChapterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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
    fun getChapter(chapterId: String){
        viewModelScope.launch {
            val chapterEntity = getChapterUseCase(chapterId)
            if (chapterEntity != null) {
                _chapterData.value = chapterEntity
                chapterEntity.branch?.let { _optionData.value = it }
            }
        }
    }
    fun setNewQuestion(option: Option){
        getChapterUseCase.setSavedGame(option)
        getChapterUseCase.setInterfaceResources(_chapterData.value.interfaceResources)
        option.nextCanonicalEventId?.let { getChapter(it) } ?: run { _optionData.value = option }
    }
    fun getSavedGame() {
        viewModelScope.launch {
            val option = getChapterUseCase.getSavedGame()
            val interfaceResources = getChapterUseCase.getInterfaceResources()
            _chapterData.update {
                it.copy(interfaceResources = interfaceResources, branch = option)
            }
        }
    }
}
