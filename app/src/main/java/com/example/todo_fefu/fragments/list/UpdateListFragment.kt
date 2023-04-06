package com.example.todo_fefu.fragments.list

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
import com.example.todo_fefu.ViewModel.ListsViewModel
import com.example.todo_fefu.ViewModel.TaskViewModel
import com.example.todo_fefu.data.task.Lists
import com.example.todo_fefu.data.task.Task
import com.example.todo_fefu.databinding.FragmentUpdateListBinding
import com.example.todo_fefu.databinding.FragmentUpdateTaskBinding
import com.example.todo_fefu.fragments.task.UpdateTaskFragmentArgs


class UpdateListFragment : Fragment() {

    private val args by navArgs<UpdateListFragmentArgs>()

    private var _binding: FragmentUpdateListBinding? = null

    private val binding get() = _binding!!

    private lateinit var mListsViewModel: ListsViewModel
    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateListBinding.inflate(inflater, container, false)
        val view = binding.root

        mListsViewModel = ViewModelProvider(this).get(ListsViewModel::class.java)
        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        binding.updateListsTitle.setText(args.currentLists.title)



        binding.confirmUpdateListsButton.setOnClickListener() {
            updateListsInDatabase()
        }

        binding.deleteListsButton.setOnClickListener(){
            deleteListsFromDatabase()
        }

        return view
    }

    private fun updateListsInDatabase() {
        val title = binding.updateListsTitle.text.toString()

        if (inputValidate(title)) {
            val lists =
                Lists(args.currentLists.id, title)

            mListsViewModel.updateLists(lists)

            Toast.makeText(requireContext(), "Successfully updated!!!", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_updateListFragment_to_listsFragment)
        } else {
            Toast.makeText(requireContext(), "Fill all inputs!!!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputValidate(title: String): Boolean {
        return !(TextUtils.isEmpty(title))
    }

    private fun deleteListsFromDatabase() {
        mListsViewModel.deleteLists(args.currentLists)
        mTaskViewModel.deleteTasksFromLists(args.currentLists.id)

        Toast.makeText(requireContext(), "Successfully deleted!!!", Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_updateListFragment_to_listsFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}