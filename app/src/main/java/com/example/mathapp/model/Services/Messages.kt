package com.example.mathapp.model

import com.google.firebase.firestore.DocumentId

private const val TITLE_LENGTH = 30

data class Message(
    @DocumentId val id: String = "",
    val message: String = "",
    val description: String = "",
    val userId: String = ""
)

fun Message.getTitle(): String {
    val isLongText = this.message.length > TITLE_LENGTH
    val endRange = if (isLongText) TITLE_LENGTH else this.message.length -1
    return this.message.substring(IntRange(0, endRange))
}
