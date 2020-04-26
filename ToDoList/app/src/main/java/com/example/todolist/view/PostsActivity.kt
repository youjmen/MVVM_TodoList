package com.example.todolist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todolist.R
import kotlinx.android.synthetic.main.activity_posts.*

class PostsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        postsTitle.text = intent.getStringExtra("title")
        postsContents.text = intent.getStringExtra("contents")
    }
}
