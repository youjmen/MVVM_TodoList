package com.example.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todolist.model.datasource.ToDo
import com.example.todolist.model.repository.repository



public class ToDoViewModel(application: Application) : AndroidViewModel(application) {


    private val repository: repository by lazy {
        repository(application)
    }



    fun getAll() : LiveData<List<ToDo>> {
        return repository.getAll()
    }

    fun insert(todo: ToDo) {
        repository.insert(todo)
    }

    fun update(todo: ToDo) {
       repository.update(todo)



    }

    fun delete(todo: ToDo) {
        repository.delete(todo)


    }
    fun deleteAll() {
        repository.deleteAll()


    }
    fun toggleCheckedState(todo: ToDo) {
        todo.isChecked = !todo.isChecked
        repository.update(todo)
    }


}




