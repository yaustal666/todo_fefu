package com.example.todo_fefu.fragments.subtask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo_fefu.ViewModel.SubtaskViewModel
import com.example.todo_fefu.adapters.SubtaskAdapter
import com.example.todo_fefu.databinding.FragmentSubtasksBinding


class SubtasksFragment : Fragment() {

    private val args by navArgs<SubtasksFragmentArgs>()

    private var _binding: FragmentSubtasksBinding? = null

    private val binding get() = _binding!!

    private lateinit var mSubtaskViewModel: SubtaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSubtasksBinding.inflate(inflater, container, false)
        val view = binding.root

        mSubtaskViewModel = ViewModelProvider(this).get(SubtaskViewModel::class.java)
        mSubtaskViewModel.getAllSubtasksFromTask(args)

        binding.addSubtaskButton.setOnClickListener(){
            val action = SubtasksFragmentDirections.actionSubtasksFragmentToAddSubtaskFragment(args.currentTask)
            findNavController().navigate(action)
        }

        val adapter = SubtaskAdapter()
        val recyclerView = binding.subtasksRecycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mSubtaskViewModel.getAllSubtasks.observe(viewLifecycleOwner, Observer { subtask ->
            adapter.setData(subtask)
            adapter.setTask(args.currentTask)
        })

        return view
    }

}
