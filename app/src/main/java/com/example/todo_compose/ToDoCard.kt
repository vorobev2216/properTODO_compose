package com.example.todo_compose

import androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent
import java.time.Instant
import java.util.Date

data class ToDoCard(
    val id: Int,
    val title: String,
    val description: String?,
    val time: Date
)

