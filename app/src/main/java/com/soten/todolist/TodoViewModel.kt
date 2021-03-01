package com.soten.todolist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel: ViewModel() {

    val todoLiveData = MutableLiveData<List<Todo>>()

    private val data = arrayListOf<Todo>()

    fun addTodo(todo: Todo) {
        data.add(todo)
        todoLiveData.value = data
    }

    fun deleteTodo(todo: Todo) {
        data.remove(todo)
        todoLiveData.value = data
    }

    fun okTodo(todo: Todo) {
        todo.isDone = !todo.isDone
        todoLiveData.value = data
    }

}