package com.example.aigame.data.entities.responses

data class GameStorageResponse(
    val actualVersion: String = "",
    val jsonVersion: String = "",
    val minVersion: String = "",
    val levels: List<Level>? = null
)

data class Level(
    val name: String,
    val enemies: List<Enemy>
)

data class Enemy(
    val name: String,
    val initialPrompt: String
)