package com.example.todo_fefu.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todo_fefu.data.TodoDatabase
import com.example.todo_fefu.data.task.Subtask
import com.example.todo_fefu.data.task.Task
import com.example.todo_fefu.fragments.subtask.SubtasksFragmentArgs
import com.example.todo_fefu.fragments.task.TaskFragmentArgs
import com.example.todo_fefu.repository.SubtaskRepository
import com.example.todo_fefu.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubtaskViewModel(application: Application): AndroidViewModel(application) {

    lateinit var getAllSubtasks: LiveData<List<Subtask>>
    private val repository: SubtaskRepository
    private lateinit var args : SubtasksFragmentArgs

    init {
        val subtaskDao = TodoDatabase.getDatabase(application).subtaskDao()
        repository = SubtaskRepository(subtaskDao)
    }

    fun addSubtask(subtask: Subtask){
        viewModelScope.launch(Dispatchers.IO){
            repository.addSubtask(subtask)
        }
    }

    fun updateSubtask(subtask: Subtask){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateSubtask(subtask)
        }
    }

    fun deleteSubtask(subtask: Subtask){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteSubtask(subtask)
        }
    }

    fun deleteSubtasksFromTask(task_id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteSubtasksFromTask(task_id)
        }
    }

    fun deleteSubtasksFromList(list_id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteSubtasksFromList(list_id)
        }
    }

    fun getAllSubtasksFromTask(subtaskFragmentArgs: SubtasksFragmentArgs){
        args = subtaskFragmentArgs
        getAllSubtasks = repository.getAllSubtasks(args.currentTask.id)
    }
}