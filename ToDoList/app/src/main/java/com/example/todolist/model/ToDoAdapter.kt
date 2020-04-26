package com.example.todolist.model

import android.content.Context
import android.content.Intent

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.databinding.TodoItemBinding
import com.example.todolist.model.datasource.ToDo
import com.example.todolist.model.repository.repository
import com.example.todolist.view.PostsActivity
import com.example.todolist.view.WriteActivity
import kotlinx.android.synthetic.main.todo_item.view.*
import org.jetbrains.anko.startActivity

class ToDoAdapter(val context: Context,
                  private val deleteClickListener: (todo: ToDo)->Unit,
                  private val modifyClickListener: (todo: ToDo)->Unit,
                  private val list: List<ToDo> = listOf<ToDo>()) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    var deleteMode = false




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        val viewHolder = ToDoViewHolder(TodoItemBinding.bind(view))

        viewHolder.binding.imageView.setOnClickListener {
            val popupMenu = PopupMenu(parent.context,it)
            popupMenu.inflate(R.menu.popup_menu)
            popupMenu.show()

            popupMenu.setOnMenuItemClickListener {
                val list = list[viewHolder.adapterPosition]
                when(it.itemId){
                    R.id.action_delete ->{
                        deleteClickListener.invoke(list)
                        true
                    }
                    R.id.action_modify->{
                        modifyClickListener.invoke(list)
                        true

                    }
                    else -> false
                }
            }
        }
        return viewHolder


    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {

        holder.binding.model = list[position]
        holder.itemView.setOnClickListener{
            val intent = Intent(context, PostsActivity::class.java)
            intent.putExtra("title", list[position].postsTitle)
            intent.putExtra("contents", list[position].postsContent)

            context.startActivity(intent)
        }
        holder.binding.checkBox.setOnClickListener {

        }
        holder.itemView.setOnLongClickListener{
          // deleteMode=true
            holder.binding.deleteBox.visibility=View.VISIBLE
            return@setOnLongClickListener true
        }
        if(deleteMode){

        }




    }

    inner class ToDoViewHolder(var binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root)




}