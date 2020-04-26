package com.example.todolist.model.datasource

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todolist.model.datasource.ToDo

@Dao
interface ToDoDao {
    @Query("SELECT * FROM to_do_content")
    fun getAll(): LiveData<List<ToDo>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todo: ToDo)

    @Query("DELETE from to_do_content")
    fun deleteAll()



    @Update
    fun update(todo: ToDo)

    @Delete
    fun delete(todo: ToDo)
}