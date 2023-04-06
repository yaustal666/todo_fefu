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
import com.example.todo_fefu.R
import com.example.todo_fefu.ViewModel.ListsViewModel
import com.example.todo_fefu.data.task.Lists
import com.example.todo_fefu.data.task.Task
import com.example.todo_fefu.databinding.FragmentAddListBinding
import com.example.todo_fefu.databinding.FragmentAddTaskBinding


class AddListFragment : Fragment() {

    private var _binding: FragmentAddListBinding? = null

    private val binding get() = _binding!!

    private lateinit var mListsViewModel: ListsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddListBinding.inflate(inflater, container, false)
        val view = binding.root

        mListsViewModel = ViewModelProvider(this).get(ListsViewModel::class.java)

        binding.confirmAddListsButton.setOnClickListener(){
            insertListsToDatabase()
        }
        return view
    }

    private fun insertListsToDatabase() {
        val title = binding.editListsTitle.text.toString()


        if(inputValidate(title)){
            val lists = Lists(0, title)
            mListsViewModel.addLists(lists)

            Toast.makeText(requireContext(), "Successfully added!!!", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_addListFragment_to_listsFragment)
        } else {
            Toast.makeText(requireContext(), "Fill all inputs!!!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputValidate(title: String): Boolean{
        return !(TextUtils.isEmpty(title))
    }

}