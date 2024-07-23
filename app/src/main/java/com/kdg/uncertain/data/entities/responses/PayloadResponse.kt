package com.kdg.uncertain.data.entities.responses

data class PayloadResponse<K>(
    val status: Int,
    val data: K
)