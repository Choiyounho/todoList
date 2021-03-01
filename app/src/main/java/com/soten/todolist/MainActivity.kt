package com.soten.todolist

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.soten.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = TodoAdapter(
                viewModel.data,
                onClickDeleteIcon = {
                    viewModel.deleteTodo(it)
                    binding.recyclerview.adapter?.notifyDataSetChanged()
                }, onClickItem = {
                    viewModel.okTodo(it)
                    binding.recyclerview.adapter?.notifyDataSetChanged()
                })
        }

        binding.buttonAdd.setOnClickListener {
            val todo = Todo(binding.editTextDescription.text.toString())
            viewModel.addTodo(todo)
            binding.editTextDescription.setText("")
            binding.recyclerview.adapter?.notifyDataSetChanged()
        }
    }

}
