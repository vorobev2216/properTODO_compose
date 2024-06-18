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


fun getToDo(): List<ToDoCard> {
    return listOf<ToDoCard>(
        ToDoCard(1, "firstr", "qhirwuehqow", Date.from(Instant.now())),
        ToDoCard(2, "firstr", "qhirwuehqow", Date.from(Instant.now())),
        ToDoCard(3, "firstr", "qhirwuehqow", Date.from(Instant.now())),
        ToDoCard(4, "firstr", "qhirwuehqow", Date.from(Instant.now())),
        ToDoCard(5, "firstr", "qhirwuehqow", Date.from(Instant.now())),
    )
}