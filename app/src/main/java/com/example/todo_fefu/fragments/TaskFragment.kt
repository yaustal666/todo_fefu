package com.example.todo_fefu.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo_fefu.R
import com.example.todo_fefu.ViewModel.TaskViewModel
import com.example.todo_fefu.adapters.TaskAdapter
import com.example.todo_fefu.databinding.FragmentTasksBinding


class TaskFragment : Fragment() {

    private var _binding: FragmentTasksBinding? = null

    private val binding get() = _binding!!

    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.addTaskButton.setOnClickListener(){
            findNavController().navigate(R.id.action_tasksFragment_to_addTaskFragment)
        }

        val adapter = TaskAdapter()
        val recyclerView = binding.tasksRecycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        mTaskViewModel.getAllTasks.observe(viewLifecycleOwner, Observer {user ->
            adapter.setData(user)
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}