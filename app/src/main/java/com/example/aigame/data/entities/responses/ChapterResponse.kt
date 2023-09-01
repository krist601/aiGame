package com.example.aigame.data.entities.responses

import java.io.Serializable

data class OptionResponse(
    val option: String,
    val text: String,
    val question: String,
    val options: List<OptionResponse>?,
    val next_chapter_id: String? = null,
    val title: String,
    val sub_title: String,
    val image: String
): Serializable