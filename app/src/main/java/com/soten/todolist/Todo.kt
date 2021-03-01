package com.soten.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Todo(val text: String, var isDone: Boolean) {

}

class TodoAdapter(private val dataSet: List<Todo>) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_todo, viewGroup, false)

        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(todoViewHolder: TodoViewHolder, position: Int) {
        val textView = todoViewHolder.itemView.findViewById<TextView>(R.id.textView_todo)
        textView.text = dataSet[position].toString()
    }

    override fun getItemCount() = dataSet.size

}
