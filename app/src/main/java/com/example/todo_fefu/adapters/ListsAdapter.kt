package com.example.todo_fefu.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_fefu.data.task.Lists
import com.example.todo_fefu.databinding.ListsRecyclerElementBinding
import com.example.todo_fefu.fragments.list.ListsFragmentDirections


class ListsAdapter: RecyclerView.Adapter<ListsAdapter.MyViewHolder>() {

    private var listsList = emptyList<Lists>()

    class MyViewHolder(val binding: ListsRecyclerElementBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListsRecyclerElementBinding.inflate(inflater, parent, false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = listsList[position]
        val context = holder.itemView.context

        holder.binding.lRecyclerElementId.text = currentItem.id.toString()
        holder.binding.lRecyclerElementTitle.text = currentItem.title


        holder.binding.goToTasksButton.setOnClickListener(){
            val action = ListsFragmentDirections.actionListsFragmentToTasksFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

        holder.binding.updateListsButton.setOnClickListener(){
            val action = ListsFragmentDirections.actionListsFragmentToUpdateListFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(lists: List<Lists>) {
        this.listsList = lists
        notifyDataSetChanged()
    }
}