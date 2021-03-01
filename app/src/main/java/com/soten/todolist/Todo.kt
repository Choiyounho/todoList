package com.soten.todolist

import android.graphics.Paint
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.soten.todolist.databinding.ItemTodoBinding

data class Todo(
    val text: String, var isDone: Boolean = false
)

class TodoAdapter(
    private val dataSet: List<Todo>,
    val onClickDeleteIcon: (todo: Todo) -> Unit,
    val onClickItem: (todo: Todo) -> Unit
) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_todo, viewGroup, false)

        return TodoViewHolder(ItemTodoBinding.bind(view))
    }

    override fun onBindViewHolder(todoViewHolder: TodoViewHolder, position: Int) {
        val todo = dataSet[position]
        todoViewHolder.binding.textViewTodo.text = todo.text

        if (todo.isDone) {
            todoViewHolder.binding.textViewTodo.apply {
                paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                setTypeface(null, Typeface.ITALIC)
            }
        } else {
            todoViewHolder.binding.textViewTodo.apply {
                paintFlags = 0
                setTypeface(null, Typeface.NORMAL)
            }
        }

        todoViewHolder.binding.root.setOnClickListener {
            onClickItem.invoke(todo)
        }

        todoViewHolder.binding.icDelete.setOnClickListener {
            onClickDeleteIcon.invoke(todo)
        }
    }

    override fun getItemCount() = dataSet.size

}
