package com.example.myfirsttodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirsttodo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = TodoAdapter(mutableListOf())
        binding.apply {
            rvTodoItems.adapter = adapter
            rvTodoItems.layoutManager = LinearLayoutManager(this@MainActivity)

            btnAddTodo.setOnClickListener {
                adapter.addTodo(Todo(etTodoTitle.text.toString()))
                etTodoTitle.text.clear()
            }

            btnDeleteTodo.setOnClickListener {
                adapter.deleteDoneTodos()
            }
        }
    }
}