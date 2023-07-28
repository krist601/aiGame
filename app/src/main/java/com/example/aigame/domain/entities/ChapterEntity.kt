package com.example.aigame.domain.entities

import com.example.aigame.data.entities.responses.ChapterResponse
import com.example.aigame.data.entities.responses.InterfaceResourcesResponse
import com.example.aigame.data.entities.responses.OptionResponse

data class ChapterEntity(
    val interfaceResources: InterfaceResources? = null,
    val branch: Option? = null
)

data class InterfaceResources(
    val title: String,
    val subtitle: String,
    val image: String
)

data class Option(
    val text: String = "",
    val question: String = "",
    val options: List<Option>? = null,
    val nextCanonicalEventId: String? = null
)

fun mapChapterToChapterEntity(chapter: ChapterResponse): ChapterEntity {
    return ChapterEntity(
        interfaceResources = mapInterfaceResourcesToInterfaceResourcesEntity(chapter.interfaceResources),
        branch = mapOptionToOption(chapter.branch)
    )
}

fun mapOptionToOption(option: OptionResponse): Option {
    return Option(
        text = option.text,
        question = option.question,
        options = option.options?.map { mapOptionToOption(it) },
        nextCanonicalEventId = option.nextCanonicalEventId
    )
}
fun mapInterfaceResourcesToInterfaceResourcesEntity(interfaceResource: InterfaceResourcesResponse): InterfaceResources {
    return InterfaceResources(
        title = interfaceResource.title,
        subtitle = interfaceResource.subtitle,
        image = interfaceResource.image
    )
}