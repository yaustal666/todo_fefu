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
import com.example.todo_fefu.ViewModel.TaskViewModel
import com.example.todo_fefu.data.task.Task
import com.example.todo_fefu.databinding.FragmentAddTaskBinding


class AddTaskFragment : Fragment() {

    private val args by navArgs<AddTaskFragmentArgs>()

    private var _binding: FragmentAddTaskBinding? = null

    private val binding get() = _binding!!

    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        val view = binding.root

        mTaskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        binding.confirmAddTaskButton.setOnClickListener(){
            insertTaskToDatabase()
        }

        return view
    }

    private fun insertTaskToDatabase() {
        val title = binding.editTitle.text.toString()
        val description = binding.editDescription.text.toString()
        val date = binding.editDate.text.toString()
        val favorite = binding.isFavoriteCheckbox.isChecked


        if(inputValidate(title, description, date)){
            val task = Task(0, args.currentLists.id, title, description, date, favorite)
            mTaskViewModel.addTask(task)

            Toast.makeText(requireContext(), "Successfully added!!!", Toast.LENGTH_LONG).show()

            val action = AddTaskFragmentDirections.actionAddTaskFragmentToTasksFragment(this.args.currentLists)
            findNavController().navigate(action)
        } else {
            Toast.makeText(requireContext(), "Fill all inputs!!!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputValidate(title: String, description: String, date: String): Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description) && TextUtils.isEmpty(date))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}