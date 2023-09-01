package com.example.aigame.domain.entities

import com.example.aigame.data.entities.responses.OptionResponse

data class ChapterEntity(
    val interfaceResources: InterfaceResources? = null,
    val branch: Option? = null
)

data class InterfaceResources(
    val title: String?,
    val subtitle: String?,
    val image: String?
)

data class Option(
    val option: String? = null,
    val text: String? = null,
    val question: String? = null,
    val options: List<Option>? = null,
    val next_chapter_id: String? = null
)

fun mapChapterToChapterEntity(chapter: OptionResponse): ChapterEntity {
    return ChapterEntity(
        interfaceResources = InterfaceResources(chapter.title, chapter.sub_title, chapter.image),
        branch = mapOptionToOption(chapter)
    )
}

fun mapOptionToOption(option: OptionResponse): Option {
    return Option(
        text = option.text,
        option = option.option,
        question = option.question,
        options = option.options?.map { mapOptionToOption(it) },
        next_chapter_id = option.next_chapter_id
    )
}
