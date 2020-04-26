package com.example.todolist.model.datasource

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "to_do_content")

data class ToDo (@PrimaryKey(autoGenerate = true) var id : Long?,
                @ColumnInfo(name = "to_do_title")var postsTitle: String?,
                @ColumnInfo(name = "written_date")var writtenDate : String?,
                @ColumnInfo(name = "posts_content")var postsContent : String?,
    @ColumnInfo(name = "is_checked")var isChecked : Boolean)  {
    constructor(): this(null,"", "","",false)
}