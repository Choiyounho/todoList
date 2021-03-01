package com.soten.todolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.soten.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val data = arrayListOf<Todo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = TodoAdapter(data,
                onClickDeleteIcon = {
                    deleteTodo(it)
                }, onClickItem = {
                    okTodo(it)
                })
        }

        binding.buttonAdd.setOnClickListener {
            addTodo()
        }
    }

    private fun addTodo() {
        val todo = Todo(binding.editTextDescription.text.toString())
        data.add(todo)
        binding.editTextDescription.setText("")
        binding.recyclerview.adapter?.notifyDataSetChanged()
    }

    private fun deleteTodo(todo: Todo) {
        data.remove(todo)
        binding.recyclerview.adapter?.notifyDataSetChanged()
    }

    private fun okTodo(todo: Todo) {
        todo.isDone = !todo.isDone
        binding.recyclerview.adapter?.notifyDataSetChanged()
    }

}
