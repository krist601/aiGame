package com.example.aigame.view_models

import androidx.lifecycle.ViewModel
import com.example.aigame.domain.use_cases.GetHomeMenuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeMenuViewModel @Inject constructor(
    private val getHomeMenuUseCase: GetHomeMenuUseCase
) : ViewModel() {
    fun hasSavedGame(): Boolean{
        return getHomeMenuUseCase.hasSavedGame()
    }
}