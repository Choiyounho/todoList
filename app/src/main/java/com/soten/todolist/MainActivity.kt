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

        binding.recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.recyclerview.adapter = TodoAdapter(data)

        binding.buttonAdd.setOnClickListener {
            addTodo()
        }
    }

    private fun addTodo() {
        val todo = Todo(binding.editTextDescription.text.toString())
        data.add(todo)
        binding.recyclerview.adapter?.notifyDataSetChanged()
    }

}
