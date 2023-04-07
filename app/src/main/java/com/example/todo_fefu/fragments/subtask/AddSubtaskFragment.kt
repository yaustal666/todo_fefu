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
import com.example.todo_fefu.ViewModel.SubtaskViewModel
import com.example.todo_fefu.data.task.Subtask
import com.example.todo_fefu.databinding.FragmentAddSubtaskBinding


class AddSubtaskFragment : Fragment() {

    private val args by navArgs<AddSubtaskFragmentArgs>()

    private var _binding: FragmentAddSubtaskBinding? = null

    private val binding get() = _binding!!

    private lateinit var mSubtaskViewModel: SubtaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddSubtaskBinding.inflate(inflater, container, false)
        val view = binding.root

        mSubtaskViewModel = ViewModelProvider(this)[SubtaskViewModel::class.java]

        binding.confirmAddSubtaskButton.setOnClickListener(){
            insertTaskToDatabase()
        }

        return view
    }

    private fun insertTaskToDatabase() {
        val title = binding.editSubtaskTitle.text.toString()
        val description = binding.editSubtaskDescription.text.toString()
        val date = binding.editSubtaskDate.text.toString()


        if(inputValidate(title, description, date)){
            val subtask = Subtask(0, args.currentTask.id, title, description, date)
            mSubtaskViewModel.addSubtask(subtask)

            Toast.makeText(requireContext(), "Successfully added!!!", Toast.LENGTH_LONG).show()

            val action = AddSubtaskFragmentDirections.actionAddSubtaskFragmentToSubtasksFragment(this.args.currentTask)
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