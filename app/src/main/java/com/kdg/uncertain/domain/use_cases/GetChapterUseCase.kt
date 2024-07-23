package com.kdg.uncertain.domain.use_cases

import com.kdg.uncertain.domain.entities.ChapterEntity
import com.kdg.uncertain.domain.entities.InterfaceResources
import com.kdg.uncertain.domain.entities.Option
import com.kdg.uncertain.domain.repositories.QuestionRepository
import javax.inject.Inject

class GetChapterUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
) {
    suspend operator fun invoke(chapterId: String): ChapterEntity? {
        return questionRepository.getChapter(chapterId)
    }
    fun getSavedGame(): Option? {
        return questionRepository.getSavedGame()
    }
    fun setSavedGame(option: Option){
        questionRepository.setSavedGame(option)
    }
    fun getInterfaceResources(): InterfaceResources?{
        return questionRepository.getInterfaceResources()
    }
    fun setInterfaceResources(interfaceResources: InterfaceResources?){
        questionRepository.setInterfaceResources(interfaceResources)
    }
    fun deleteSavedGame(){
        questionRepository.deleteSavedGame()
    }
}