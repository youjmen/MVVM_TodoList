package com.example.todolist.view


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.R
import com.example.todolist.model.datasource.ToDo
import com.example.todolist.viewmodel.ToDoViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), ToDoAdapter.TodoItemClickListener {


    private lateinit var toDoViewModel: ToDoViewModel
    var delete = false
    private lateinit var todoAdapter: ToDoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar2)

        toDoViewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)
        recyclerView.layoutManager = LinearLayoutManager(this)
        todoAdapter = ToDoAdapter(this, this)
        todoAdapter.setHasStableIds(true)
        recyclerView.adapter=todoAdapter





        toDoViewModel.getAll().observe(this, Observer {


            todoAdapter.apply {
                list = it
                notifyDataSetChanged()
            }
        })








        writingButton.setOnClickListener {
            startActivity<WriteActivity>()

        }


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if(delete)
            menuInflater.inflate(R.menu.menu_delete_mode, menu)
        else
            menuInflater.inflate(R.menu.menu_delete, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.delete_mode -> {

                delete= !delete
                todoAdapter.deleteMode=true
                todoAdapter.notifyDataSetChanged()
                invalidateOptionsMenu()





            }
            R.id.select_all->{



            }
            R.id.cancel_action->{
                delete=!delete
                todoAdapter.deleteMode=false
                todoAdapter.notifyDataSetChanged()

                invalidateOptionsMenu()
            }
            R.id.delete->{
                toDoViewModel.deleteAll()

            }
        }
        return super.onOptionsItemSelected(item)
    }
    fun setDeleteMode(){
        delete=true
        invalidateOptionsMenu()
    }

    override fun onDeleteClicked(todoItem: ToDo) {
        toDoViewModel.delete(todoItem)
    }

    override fun onCheckClicked(todoItem: ToDo) {
        toDoViewModel.toggleCheckedState(todoItem)
    }

    override fun onLongClicked() {
       setDeleteMode()
    }




}
