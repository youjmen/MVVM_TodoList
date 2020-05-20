package com.example.todolist.view


import android.os.Bundle
import android.util.Log
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
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), ToDoAdapter.TodoItemClickListener, ToDoAdapter.selection {


    private lateinit var toDoViewModel: ToDoViewModel
    var delete = false
    var selectall = false
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
        if(delete&&selectall)
            menuInflater.inflate(R.menu.menu_selecteall_cancel,menu)
        else if(delete)
            menuInflater.inflate(R.menu.menu_delete_mode, menu)
        else
            menuInflater.inflate(R.menu.menu_delete,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.delete_mode -> {
                if(todoAdapter.list.isEmpty()){
                    toast("지울 목록이 없습니다.")
                }
                else {
                    delete =!delete
                    todoAdapter.selectAll=1

                    todoAdapter.deleteMode = true
                    todoAdapter.notifyDataSetChanged()
                    invalidateOptionsMenu()
                }




            }
            R.id.select_all->{
                selectAll()



            }
            R.id.cancel_action->{
                cancelDelete()
            }
            R.id.delete->{
                delete()


            }
            R.id.cancel_select->{
                cancelSelect()

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

    override fun onCheckBoxClicked(todoItem: ToDo) {
        toDoViewModel.toggleCheckedState(todoItem)
    }
    override fun onDeleteBoxClicked(todoItem: ToDo) {
        todoAdapter.selectAll=0

        toDoViewModel.toggleDeleteCheckedState(todoItem)
    }

    override fun checkDeleteTrue(todoItem: ToDo) {
        toDoViewModel.toggleDeleteCheckedStateTrue(todoItem)



    }

    override fun checkDeleteFalse(todoItem: ToDo) {
        toDoViewModel.toggleDeleteCheckedStateFalse(todoItem)
    }

    override fun onLongClicked() {
       setDeleteMode()
    }

    override fun selectAll() {
        todoAdapter.selectAll=2
        selectall=!selectall

        todoAdapter.notifyDataSetChanged()
        invalidateOptionsMenu()

    }

    override fun cancelDelete() {
        delete=false
        selectall=false
        todoAdapter.deleteMode=false
        todoAdapter.selectAll=1
        todoAdapter.notifyDataSetChanged()


        invalidateOptionsMenu()
    }
    override fun cancelSelect(){
        todoAdapter.selectAll=1

        selectall=!selectall


        todoAdapter.notifyDataSetChanged()
        invalidateOptionsMenu()

    }
    fun delete(){
        for(i in 0..todoAdapter.itemCount-1){
            if(todoAdapter.list[i].deleteChecked){
                toDoViewModel.delete(todoAdapter.list[i])
            }

        }
        selectall=false
        delete=false
        todoAdapter.deleteMode=false
        todoAdapter.notifyDataSetChanged()
        invalidateOptionsMenu()

    }

    override fun onPause() {
        super.onPause()
        Log.d("[onPause]","executed")
        cancelDelete()
    }




}
