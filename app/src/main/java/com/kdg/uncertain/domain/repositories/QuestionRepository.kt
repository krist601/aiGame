package com.kdg.uncertain.domain.repositories

import com.kdg.uncertain.data.data_sources.local.QuestionLocalDataSource
import com.kdg.uncertain.data.data_sources.network.QuestionNetworkDataSource
import com.kdg.uncertain.domain.entities.ChapterEntity
import com.kdg.uncertain.domain.entities.InterfaceResources
import com.kdg.uncertain.domain.entities.Option
import com.kdg.uncertain.domain.entities.mapChapterToChapterEntity
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val questionLocalDataSource: QuestionLocalDataSource,
    private val questionNetworkDataSource: QuestionNetworkDataSource
) {

    suspend fun getChapter(chapterId: String): ChapterEntity? {
        return try {
            val chapterResponse = questionNetworkDataSource.getChapter(chapterId)
            val chapterEntity = mapChapterToChapterEntity(chapterResponse)
            questionLocalDataSource.setChapterResponse(chapterResponse, chapterId)
            chapterEntity
        } catch (e: Exception) {
            questionLocalDataSource.getChapterResponse(chapterId)?.let{ mapChapterToChapterEntity(it) }
        }
    }
    fun getSavedGame(): Option? {
        return questionLocalDataSource.getSavedGame()
    }
    fun setSavedGame(option: Option){
        questionLocalDataSource.setSavedGame(option)
    }
    fun getInterfaceResources(): InterfaceResources?{
        return questionLocalDataSource.getInterfaceResources()
    }
    fun setInterfaceResources(interfaceResources: InterfaceResources?){
        questionLocalDataSource.setInterfaceResources(interfaceResources)
    }
    fun hasSavedGame(): Boolean{
        return questionLocalDataSource.hasSavedGame()
    }
    fun deleteSavedGame(){
        questionLocalDataSource.deleteSavedGame()
    }
}

