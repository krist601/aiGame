package com.example.aigame.data.entities.responses

data class PayloadResponse<K>(
    val status: Int,
    val data: K
)