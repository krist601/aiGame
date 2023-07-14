package com.example.aigame.data.entities.requests

data class ChatMessageRequest(
    val model: String,
    val messages: List<Message>
)

data class Message(
    val role: String,
    val content: String
)