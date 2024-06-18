package com.example.todo_compose

import android.icu.text.CaseMap.Title
import androidx.core.graphics.Insets
import java.time.Instant
import java.util.Date

object ToDoManager {
    private val todoList = mutableListOf<ToDoCard>()


    fun getTodo(): MutableList<ToDoCard> {
        return todoList
    }

    fun addTodo(title: String, description: String?) {
        todoList.add(
            ToDoCard(System.currentTimeMillis().toInt(), title, description, Date.from(Instant.now())))

    }

    fun deleteTodo(id: Int) {
        todoList.removeIf{
            it.id == id
        }

    }
}