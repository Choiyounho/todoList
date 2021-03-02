package com.soten.todolist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TodoViewModel : ViewModel() {

    val db = Firebase.firestore

    val todoLiveData = MutableLiveData<List<DocumentSnapshot>>()

    init {
        fetchData()
    }

    fun fetchData() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            db.collection(user.uid)
                .addSnapshotListener { value, error ->
                    if (error != null) {
                        return@addSnapshotListener
                    }

                    if (value != null) {
                        todoLiveData.value = value.documents
                    }
                }
        }
    }

    fun addTodo(todo: Todo) {
        FirebaseAuth.getInstance().currentUser?.let { user ->
            db.collection(user.uid)
                .add(todo)
        }
    }

    fun deleteTodo(todo: DocumentSnapshot) {
        FirebaseAuth.getInstance().currentUser?.let { user ->
            db.collection(user.uid)
                .document(todo.id)
                .delete()
        }
    }

    fun okTodo(todo: DocumentSnapshot) {
//        todo.isDone = !todo.isDone
//        todoLiveData.value = data
    }

}