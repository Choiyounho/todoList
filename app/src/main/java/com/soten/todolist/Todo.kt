package com.soten.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.soten.todolist.databinding.ItemTodoBinding

data class Todo(val text: String, var isDone: Boolean = false)

class TodoAdapter(private val dataSet: List<Todo>) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_todo, viewGroup, false)

        return TodoViewHolder(ItemTodoBinding.bind(view))
    }

    override fun onBindViewHolder(todoViewHolder: TodoViewHolder, position: Int) {
        todoViewHolder.binding.textViewTodo.text = dataSet[position].text
    }

    override fun getItemCount() = dataSet.size

}
