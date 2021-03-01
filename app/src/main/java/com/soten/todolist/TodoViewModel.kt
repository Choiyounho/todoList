package com.soten.todolist

import androidx.lifecycle.ViewModel

class TodoViewModel: ViewModel() {

    val data = arrayListOf<Todo>()

    fun addTodo(todo: Todo) {
        data.add(todo)
    }

    fun deleteTodo(todo: Todo) {
        data.remove(todo)
    }

    fun okTodo(todo: Todo) {
        todo.isDone = !todo.isDone
    }
    
}