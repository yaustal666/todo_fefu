package com.example.todo_fefu.fragments.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo_fefu.R
import com.example.todo_fefu.ViewModel.TaskViewModel
import com.example.todo_fefu.adapters.TaskAdapter
import com.example.todo_fefu.databinding.FragmentTasksBinding


class TaskFragment : Fragment() {

    private val args by navArgs<TaskFragmentArgs>()

    private var _binding: FragmentTasksBinding? = null

    private val binding get() = _binding!!

    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.addTaskButton.setOnClickListener() {
            val action = TaskFragmentDirections.actionTasksFragmentToAddTaskFragment(args.currentLists)
            findNavController().navigate(action)
        }

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        mTaskViewModel.getAllTasksFromLists(args)

        val adapter = TaskAdapter()
        val recyclerView = binding.tasksRecycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        mTaskViewModel.getAllTasks.observe(viewLifecycleOwner, Observer { task ->
            adapter.setData(task)
            adapter.setList(args.currentLists)
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}