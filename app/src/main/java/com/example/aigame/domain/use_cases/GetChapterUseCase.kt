package com.example.aigame.domain.use_cases

import com.example.aigame.domain.entities.ChapterEntity
import com.example.aigame.domain.repositories.QuestionRepository
import javax.inject.Inject

class GetChapterUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
) {
    suspend operator fun invoke(chapterId: String): ChapterEntity? {
        return questionRepository.getChapter(chapterId)
    }
}