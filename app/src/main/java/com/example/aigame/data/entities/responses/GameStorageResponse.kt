package com.example.aigame.data.entities.responses

data class GameStorageResponse(
    val actualVersion: String = "",
    val jsonVersion: String = "",
    val minVersion: String = ""
)

data class Level(
    val name: String,
    val type: String?,
    val step: List<Step>
)

data class Step(
    val name: String,
    val type: String?,
    val initialPrompt: String,
    val story: String,
    val maxResponses: Int
)