package com.example.todo_compose

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.Instant
import java.util.Date

class ToDoViewModel: ViewModel() {
    private var _todoList = MutableLiveData<List<ToDoCard>>()
    val todoList = _todoList


    fun getTodo(){
         _todoList.value = ToDoManager.getTodo()
    }

    fun addTodo(title: String, description: String?) {
        ToDoManager.addTodo(title,description)
        getTodo()
    }

    fun deleteTodo(id: Int) {
        ToDoManager.deleteTodo(id)
        getTodo()

    }
}