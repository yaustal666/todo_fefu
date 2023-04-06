package com.example.todo_fefu.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_fefu.R
import com.example.todo_fefu.data.task.Task
import com.example.todo_fefu.databinding.TaskRecyclerElementBinding

class TaskAdapter: RecyclerView.Adapter<TaskAdapter.MyViewHolder>() {

    private var taskList = emptyList<Task>()

    class MyViewHolder(val binding: TaskRecyclerElementBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TaskRecyclerElementBinding.inflate(inflater, parent, false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = taskList[position]
        val context = holder.itemView.context

        holder.binding.tRecyclerElementTitle.text = currentItem.title
        holder.binding.tRecyclerElementDescription.text = currentItem.description
        holder.binding.tRecyclerElementDate.text = currentItem.date
    }

    fun setData(task: List<Task>) {
        this.taskList = task
        notifyDataSetChanged()
    }
}