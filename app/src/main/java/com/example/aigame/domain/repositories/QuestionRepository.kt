package com.example.aigame.domain.repositories

import com.example.aigame.data.services.RetrofitMS
import com.example.aigame.domain.entities.ChapterEntity
import com.example.aigame.domain.entities.InterfaceResources
import com.example.aigame.domain.entities.Option
import com.example.aigame.domain.entities.mapChapterToChapterEntity
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val questionService: RetrofitMS
) {

    suspend fun getChapter(chapterId: String): ChapterEntity? {
        return try {
            mapChapterToChapterEntity(questionService.getChapter(chapterId))
        } catch (e: Exception) {
            createMockChapterResponse(chapterId)
        }
    }

    private fun createMockChapterResponse(userId: String): ChapterEntity {
        val interfaceResources = InterfaceResources(
            title = "Raccoon",
            subtitle = "of the evil death",
            image = "https://static.wikia.nocookie.net/clash-of-clans/images/c/c5/Dragón_info.png/revision/latest/scale-to-width-down/120?cb=20210819010118&path-prefix=es"
        )

        // Create mock Options
        val option1 = Option(
            question = "you find the raccoon of the evil death, he tries to rape you, what do you want to do?",
            options = listOf(
                Option(
                    text = "Let it rape you",
                    question = "Question for Option 2",
                    options = null
                ),
                Option(
                    text = "Take the wheels in the rape",
                    question = "You star to enjoy it, and got in a mad state that tries to fuck other animals in the surrounded area",
                    options = listOf(
                        Option(
                            text = "Rape a bunny",
                            question = "You enjoy it, good for you you sick bastard",
                            nextCanonicalEventId = "1",
                            options = null
                        ),
                        Option(
                            text = "Rape a chicken",
                            question = "You enjoy it, good for you you sick bastard",
                            nextCanonicalEventId = "1",
                            options = null
                        ),
                        Option(
                            text = "Rape a squirrel",
                            question = "You enjoy it, good for you you sick bastard",
                            nextCanonicalEventId = "1",
                            options = null
                        )
                    )
                ),
                Option(
                    text = "Run like a little bunny",
                    question = "you are so gay, you die of venereal disease",
                    options = null
                ),
            )
        )

        return ChapterEntity(interfaceResources = interfaceResources, branch = option1)
    }
}

