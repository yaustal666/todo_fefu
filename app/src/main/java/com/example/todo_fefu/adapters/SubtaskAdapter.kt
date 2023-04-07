package com.example.todo_fefu.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_fefu.data.task.Subtask
import com.example.todo_fefu.data.task.Task
import com.example.todo_fefu.databinding.SubtaskRecyclerElementBinding
import com.example.todo_fefu.fragments.subtask.SubtasksFragmentDirections


class SubtaskAdapter: RecyclerView.Adapter<SubtaskAdapter.MyViewHolder>() {

    private var subtaskList = emptyList<Subtask>()

    private var currentTask = Task(0, 0, "a", "b", "c", false)

    class MyViewHolder(val binding: SubtaskRecyclerElementBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SubtaskRecyclerElementBinding.inflate(inflater, parent, false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return subtaskList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = subtaskList[position]
        val context = holder.itemView.context

        holder.binding.subRecyclerElementTitle.text = currentItem.title
        holder.binding.subRecyclerElementDescription.text = currentItem.description
        holder.binding.subRecyclerElementDate.text = currentItem.date

        holder.binding.UpdateSubtaskButton.setOnClickListener(){
            val action = SubtasksFragmentDirections.actionSubtasksFragmentToUpdateSubtaskFragment(currentTask, currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(subtask: List<Subtask>) {
        this.subtaskList = subtask
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setTask(task: Task) {
        this.currentTask = task
        notifyDataSetChanged()
    }
}