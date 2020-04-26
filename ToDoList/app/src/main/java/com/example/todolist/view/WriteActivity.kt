package com.example.todolist.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.R
import com.example.todolist.model.datasource.ToDo
import com.example.todolist.viewmodel.ToDoViewModel
import kotlinx.android.synthetic.main.activity_write.*
import kotlinx.android.synthetic.main.todo_item.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class WriteActivity : AppCompatActivity() {

    private lateinit var toDoViewModel: ToDoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        toDoViewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)

        add_button.setOnClickListener{
            val todos = ToDo()
            todos.postsTitle= inputTitle.text.toString()
            todos.postsContent= inputContents.text.toString()
            todos.writtenDate = getLocalTime()
            toDoViewModel.insert(todos)
            finish()
        }
    }
    companion object {
        fun getLocalTime(): String? {
            lateinit var formatted: String
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val current = LocalDateTime.now().plusHours(9)
                val formatter = DateTimeFormatter.ofPattern("HH : mm")
                formatted = current.format(formatter)


            }
            return formatted

        }
    }

}
