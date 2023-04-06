package com.example.todo_fefu.fragments.list

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
import com.example.todo_fefu.ViewModel.ListsViewModel
import com.example.todo_fefu.adapters.ListsAdapter
import com.example.todo_fefu.databinding.FragmentListsBinding


class ListsFragment : Fragment() {

    private var _binding: FragmentListsBinding? = null

    private val binding get() = _binding!!

    private lateinit var mListsViewModel: ListsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListsBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.addListsButton.setOnClickListener(){
            findNavController().navigate(R.id.action_listsFragment_to_addListFragment)
        }


        val adapter = ListsAdapter()
        val recyclerView = binding.listsRecycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mListsViewModel = ViewModelProvider(this).get(ListsViewModel::class.java)
        mListsViewModel.getAllLists.observe(viewLifecycleOwner, Observer { lists ->
            adapter.setData(lists)
        })
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}