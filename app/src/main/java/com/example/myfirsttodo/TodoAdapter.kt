package com.example.myfirsttodo

import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirsttodo.databinding.ItemTodoBinding

class TodoAdapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val binding = ItemTodoBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = todos[position]
        holder.binding.apply {
            tvTodoTitle.text = currentTodo.title
            cbDone.isChecked = currentTodo.isDone
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThroughTodo(tvTodoTitle, isChecked)
                currentTodo.isDone = !currentTodo.isDone
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    private fun toggleStrikeThroughTodo(tv: TextView, isDone: Boolean) {
        if (isDone) {
            tv.paintFlags = tv.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            tv.paintFlags = tv.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeAll { todo -> todo.isDone }
        notifyDataSetChanged()
    }
}