package com.soten.todolist

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
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
                emptyList(),
                onClickDeleteIcon = {
                    viewModel.deleteTodo(it)
                }, onClickItem = {
                    viewModel.okTodo(it)
                })
        }

        binding.buttonAdd.setOnClickListener {
            val todo = Todo(binding.editTextDescription.text.toString())
            viewModel.addTodo(todo)
            binding.editTextDescription.setText("")
            binding.recyclerview.adapter?.notifyDataSetChanged()
        }

        viewModel.todoLiveData.observe(this@MainActivity, Observer {
            (binding.recyclerview.adapter as TodoAdapter).setData(it)
        })
    }

}
