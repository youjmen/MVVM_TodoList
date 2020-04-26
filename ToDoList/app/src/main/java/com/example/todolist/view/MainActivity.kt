package com.example.todolist.view


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.R
import com.example.todolist.model.ToDoAdapter
import com.example.todolist.model.datasource.ToDo
import com.example.todolist.viewmodel.ToDoViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.todo_item.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {


    private lateinit var toDoViewModel: ToDoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar2)






        toDoViewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)



        toDoViewModel.getAll().observe(this, Observer {
            recyclerView.layoutManager = LinearLayoutManager(this)
            var todoAdapter= ToDoAdapter(this,deleteClickListener = {
                toDoViewModel.delete(it)
            },modifyClickListener = {
                val intent = Intent(this,EditActivity::class.java)
                intent.putExtra("id",it.id)
                intent.putExtra("title",it.postsTitle)
                intent.putExtra("contents",it.postsContent)
                startActivity(intent)}, list = it)
            recyclerView.adapter = todoAdapter
            todoAdapter.deleteMode=true
            todoAdapter.notifyDataSetChanged()



        })








        writingButton.setOnClickListener {
            startActivity<WriteActivity>()
        }


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_delete, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.deleteall -> {

                    toDoViewModel.deleteAll()




            }
        }
        return super.onOptionsItemSelected(item)
    }
}
