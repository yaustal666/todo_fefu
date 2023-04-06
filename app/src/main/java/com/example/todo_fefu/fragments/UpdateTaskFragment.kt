package com.example.todo_fefu.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todo_fefu.R
import com.example.todo_fefu.ViewModel.TaskViewModel
import com.example.todo_fefu.data.task.Task
import com.example.todo_fefu.databinding.FragmentTasksBinding
import com.example.todo_fefu.databinding.FragmentUpdateTaskBinding

class UpdateTaskFragment : Fragment() {

    private val args by navArgs<UpdateTaskFragmentArgs>()

    private var _binding: FragmentUpdateTaskBinding? = null

    private val binding get() = _binding!!

    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateTaskBinding.inflate(inflater, container, false)
        val view = binding.root

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        binding.updateTitle.setText(args.currentTask.title)
        binding.updateDescription.setText(args.currentTask.description)
        binding.updateDate.setText(args.currentTask.date)


        binding.confirmUpdateTaskButton.setOnClickListener() {
            updateTaskInDatabase()
        }
        return view
    }

    private fun updateTaskInDatabase() {
        val title = binding.updateTitle.text.toString()
        val description = binding.updateDescription.text.toString()
        val date = binding.updateDate.text.toString()


        if (inputValidate(title, description, date)) {
            val task = Task(args.currentTask.id, args.currentTask.list_id, title, description, date, false)

            mTaskViewModel.updateTask(task)

            Toast.makeText(requireContext(), "Successfully added!!!", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_updateTaskFragment_to_tasksFragment)
        } else {
            Toast.makeText(requireContext(), "Fill all inputs!!!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputValidate(title: String, description: String, date: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description) && TextUtils.isEmpty(
            date
        ))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}