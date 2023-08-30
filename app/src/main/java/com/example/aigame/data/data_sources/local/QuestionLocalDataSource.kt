package com.example.aigame.data.data_sources.local

import com.example.aigame.data.entities.responses.ChapterResponse
import com.example.aigame.data.util.SharedPreferencesHelper
import com.example.aigame.domain.entities.InterfaceResources
import com.example.aigame.domain.entities.Option
import javax.inject.Inject

class QuestionLocalDataSource @Inject constructor(
    private val sharedPreferencesHelper: SharedPreferencesHelper
){
    fun getChapterResponse(chapterId: String): ChapterResponse? {
        return sharedPreferencesHelper.retrieveData<ChapterResponse>("chapterResponse:$chapterId", ChapterResponse::class.java)
    }
    fun setChapterResponse(chapterResponse: ChapterResponse, chapterId: String) {
        sharedPreferencesHelper.storeData("chapterResponse:$chapterId", chapterResponse)
    }
    fun getSavedGame(): Option? {
        return sharedPreferencesHelper.retrieveData<Option>("savedGame", Option::class.java)
    }
    fun setSavedGame(option: Option){
        sharedPreferencesHelper.storeData("savedGame", option)
    }
    fun getInterfaceResources(): InterfaceResources?{
        return sharedPreferencesHelper.retrieveData<InterfaceResources>("savedGameInterfaceResources", InterfaceResources::class.java)
    }
    fun setInterfaceResources(interfaceResources: InterfaceResources?){
        sharedPreferencesHelper.storeData("savedGameInterfaceResources", interfaceResources)
    }
}