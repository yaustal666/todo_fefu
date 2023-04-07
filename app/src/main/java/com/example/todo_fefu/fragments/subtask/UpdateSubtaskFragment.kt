package com.example.todo_fefu.fragments.subtask

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
import com.example.todo_fefu.data.task.Subtask
import com.example.todo_fefu.data.task.Task
import com.example.todo_fefu.databinding.FragmentUpdateSubtaskBinding
import com.example.todo_fefu.databinding.FragmentUpdateTaskBinding
import com.example.todo_fefu.fragments.task.UpdateTaskFragmentArgs

class UpdateSubtaskFragment : Fragment() {

    private val args by navArgs<UpdateSubtaskFragmentArgs>()

    private var _binding: FragmentUpdateSubtaskBinding? = null

    private val binding get() = _binding!!

    private lateinit var mSubtaskViewModel: SubtaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateSubtaskBinding.inflate(inflater, container, false)
        val view = binding.root

        mSubtaskViewModel = ViewModelProvider(this).get(SubtaskViewModel::class.java)

        binding.updateSubtaskTitle.setText(args.currentSubtask.title)
        binding.updateSubtaskDescription.setText(args.currentSubtask.description)
        binding.updateSubtaskDate.setText(args.currentSubtask.date)


        binding.confirmUpdateSubtaskButton.setOnClickListener() {
            updateSubtaskInDatabase()
        }

        binding.deleteSubtaskButton.setOnClickListener() {
            deleteSubtaskFromDatabase()
        }


        return view
    }

    private fun updateSubtaskInDatabase() {
        val title = binding.updateSubtaskTitle.text.toString()
        val description = binding.updateSubtaskDescription.text.toString()
        val date = binding.updateSubtaskDate.text.toString()


        if (inputValidate(title, description, date)) {
            val subtask =
                Subtask(args.currentSubtask.id, args.currentSubtask.task_id, title, description, date)

            mSubtaskViewModel.updateSubtask(subtask)

            Toast.makeText(requireContext(), "Successfully updated!!!", Toast.LENGTH_LONG).show()

            val action =
                UpdateSubtaskFragmentDirections.actionUpdateSubtaskFragmentToSubtasksFragment(this.args.currentTask)
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

    private fun deleteSubtaskFromDatabase() {
        mSubtaskViewModel.deleteSubtask(args.currentSubtask)
        Toast.makeText(requireContext(), "Successfully deleted!!!", Toast.LENGTH_LONG).show()
        val action =
            UpdateSubtaskFragmentDirections.actionUpdateSubtaskFragmentToSubtasksFragment(this.args.currentTask)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}