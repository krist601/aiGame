package com.example.aigame.data.entities.responses

data class ChapterResponse(
    val interfaceResources: InterfaceResourcesResponse?,
    val branch: OptionResponse
)

data class InterfaceResourcesResponse(
    val title: String,
    val subtitle: String,
    val image: String
)

data class OptionResponse(
    val option: String,
    val text: String,
    val question: String,
    val options: List<OptionResponse>?,
    val nextCanonicalEventId: String? = null
)