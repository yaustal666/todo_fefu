package com.example.todo_fefu.fragments.task

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
import com.example.todo_fefu.ViewModel.SubtaskViewModel
import com.example.todo_fefu.ViewModel.TaskViewModel
import com.example.todo_fefu.data.task.Task
import com.example.todo_fefu.databinding.FragmentUpdateTaskBinding
import com.example.todo_fefu.fragments.task.UpdateTaskFragmentArgs

class UpdateTaskFragment : Fragment() {

    private val args by navArgs<UpdateTaskFragmentArgs>()

    private var _binding: FragmentUpdateTaskBinding? = null

    private val binding get() = _binding!!

    private lateinit var mTaskViewModel: TaskViewModel
    private lateinit var mSubtaskViewModel: SubtaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateTaskBinding.inflate(inflater, container, false)
        val view = binding.root

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        mSubtaskViewModel = ViewModelProvider(this).get(SubtaskViewModel::class.java)

        binding.updateTitle.setText(args.currentTask.title)
        binding.updateDescription.setText(args.currentTask.description)
        binding.updateDate.setText(args.currentTask.date)
        binding.updateFavorite.isChecked = args.currentTask.favorite


        binding.confirmUpdateTaskButton.setOnClickListener() {
            updateTaskInDatabase()
        }

        binding.deleteTaskButton.setOnClickListener() {
            deleteTaskFromDatabase()
        }

        binding.goToSubtasksButton.setOnClickListener() {
            val action =
                UpdateTaskFragmentDirections.actionUpdateTaskFragmentToSubtasksFragment(args.currentTask)
            findNavController()
                .navigate(action)
        }

        return view
    }

    private fun updateTaskInDatabase() {
        val title = binding.updateTitle.text.toString()
        val description = binding.updateDescription.text.toString()
        val date = binding.updateDate.text.toString()
        val favorite = binding.updateFavorite.isChecked


        if (inputValidate(title, description, date)) {
            val task =
                Task(args.currentTask.id, args.currentTask.list_id, title, description, date, favorite)

            mTaskViewModel.updateTask(task)

            Toast.makeText(requireContext(), "Successfully updated!!!", Toast.LENGTH_LONG).show()

            val action =
                UpdateTaskFragmentDirections.actionUpdateTaskFragmentToTasksFragment(this.args.currentLists)
            findNavController().navigate(action)
        } else {
            Toast.makeText(requireContext(), "Fill all inputs!!!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputValidate(title: String, description: String, date: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description) && TextUtils.isEmpty(
            date
        ))
    }

    private fun deleteTaskFromDatabase() {
        mTaskViewModel.deleteTask(args.currentTask)
        mSubtaskViewModel.deleteSubtasksFromTask(args.currentTask.id)
        Toast.makeText(requireContext(), "Successfully deleted!!!", Toast.LENGTH_LONG).show()
        val action =
            UpdateTaskFragmentDirections.actionUpdateTaskFragmentToTasksFragment(this.args.currentLists)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}