package com.example.todolist.view

import android.content.Context
import android.content.Intent
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.databinding.TodoItemBinding
import com.example.todolist.model.datasource.ToDo
import kotlinx.android.synthetic.main.todo_item.view.*

class ToDoAdapter(private val context: Context,

                  private val deleteModeClickListener: TodoItemClickListener,
                  var list: List<ToDo> = listOf<ToDo>()) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {




    var deleteMode = false
    var selectAll =false

    var todoItemClickListener = deleteModeClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        val viewHolder = ToDoViewHolder(
            TodoItemBinding.bind(view)
        )


        viewHolder.binding.imageView.setOnClickListener {
                        val popupMenu = PopupMenu(parent.context,it)
                        popupMenu.inflate(R.menu.popup_menu)
                        popupMenu.show()

                        popupMenu.setOnMenuItemClickListener {
                            val list = list[viewHolder.adapterPosition]
                            when(it.itemId){
                                R.id.action_delete ->{
                                    todoItemClickListener.onDeleteClicked(list)




                                    true
                                }
                                R.id.action_modify->{
                                    val intent =Intent(context,EditActivity::class.java)
                                    intent.putExtra("id",list.id)
                                    intent.putExtra("title",list.postsTitle)
                                    intent.putExtra("contents",list.postsContent)
                                    context.startActivity(intent)

                                    true

                                }
                                else -> false
                }
            }
        }
        viewHolder.itemView.setOnLongClickListener {

            todoItemClickListener.onLongClicked()
            deleteMode=true
            notifyDataSetChanged()


            return@setOnLongClickListener true
        }



        return viewHolder


    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {

        holder.binding.model = list[position]
        holder.itemView.setOnClickListener{
            val intent = Intent(context, PostsActivity::class.java)
            intent.putExtra("title", list[position].postsTitle)
            intent.putExtra("contents", list[position].postsContent)

            context.startActivity(intent)
        }

        holder.itemView.checkBox.setOnClickListener {
            Log.d("it works","ok")
            todoItemClickListener.onCheckClicked(list[position])
        }

        if(deleteMode){
            holder.binding.deleteBox.visibility=View.VISIBLE

        }else{
            holder.binding.deleteBox.visibility=View.GONE

        }











    }

     class ToDoViewHolder(var binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root)



    interface TodoItemClickListener {
        fun onDeleteClicked(todoItem: ToDo)
        fun onLongClicked()
        fun onCheckClicked(todoItem: ToDo)
    }



}