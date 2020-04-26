package com.example.todolist.model.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.todolist.model.datasource.AppDatabase
import com.example.todolist.model.datasource.ToDo
import com.example.todolist.model.datasource.ToDoDao
import io.reactivex.Observable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class repository(application: Application) {
    private val todoDao: ToDoDao by lazy {
        val db = AppDatabase.getInstance(application)!!
        db.todoDao()
    }


    fun getAll(): LiveData<List<ToDo>> {

        return todoDao.getAll()
    }

    fun insert(todo: ToDo){
        CoroutineScope(Dispatchers.IO).launch {
            todoDao.insert(todo)
        }

    }



    fun update(todo: ToDo){
        CoroutineScope(Dispatchers.IO).launch {
            todoDao.update(todo)
        }
    }

    fun delete(todo: ToDo){
        CoroutineScope(Dispatchers.IO).launch {
            todoDao.delete(todo)
        }
    }
    fun deleteAll(){
        CoroutineScope(Dispatchers.IO).launch {
            todoDao.deleteAll()
        }
    }

}