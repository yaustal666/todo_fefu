package com.example.todo_fefu.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todo_fefu.data.TodoDatabase
import com.example.todo_fefu.data.task.Task
import com.example.todo_fefu.fragments.task.TaskFragmentArgs
import com.example.todo_fefu.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application): AndroidViewModel(application) {

    lateinit var getAllTasks: LiveData<List<Task>>
    private val repository: TaskRepository
    private lateinit var args : TaskFragmentArgs

    init {
        val taskDao = TodoDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
    }

    fun addTask(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            repository.addTask(task)
        }
    }

    fun updateTask(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateTask(task)
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteTask(task)
        }
    }

    fun deleteTasksFromLists(list_id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTasksFromLists(list_id)
        }
    }

    fun getAllTasksFromLists(taskFragmentArgs: TaskFragmentArgs){
        args = taskFragmentArgs
        getAllTasks = repository.getAllTasks(args.currentLists.id)
    }
}