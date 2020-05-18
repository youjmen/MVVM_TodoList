package com.example.todolist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.R
import com.example.todolist.model.datasource.ToDo
import com.example.todolist.viewmodel.ToDoViewModel
import kotlinx.android.synthetic.main.activity_edit.*



class EditActivity : AppCompatActivity() {
    private lateinit var toDoViewModel: ToDoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        toDoViewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)
        val todos = ToDo(intent.getLongExtra("id",0),"","","",false,false)

        editInputTitle.setText(intent.getStringExtra("title"))
        editInputContents.setText(intent.getStringExtra("contents"))
        edit_button.setOnClickListener {
            todos.postsTitle = editInputTitle.text.toString()
            todos.postsContent = editInputContents.text.toString()
            todos.writtenDate=WriteActivity.getLocalTime()
            toDoViewModel.update(todos)
            finish()
        }



    }
}
