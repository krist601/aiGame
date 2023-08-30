package com.example.aigame.data.entities.responses

import java.io.Serializable
import java.util.UUID

data class ChapterResponse(
    val interface_resource: InterfaceResourcesResponse?,
    val branch: OptionResponse
): Serializable

data class InterfaceResourcesResponse(
    val title: String,
    val sub_title: String,
    val image: String
): Serializable

data class OptionResponse(
    val id: String = UUID.randomUUID().toString(),
    val option: String,
    val text: String,
    val question: String,
    val options: List<OptionResponse>?,
    val nextCanonicalEventId: String? = null
): Serializable