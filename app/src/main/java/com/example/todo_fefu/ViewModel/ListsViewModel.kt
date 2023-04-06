package com.example.todo_fefu.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todo_fefu.data.TodoDatabase
import com.example.todo_fefu.data.task.Lists
import com.example.todo_fefu.repository.ListsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListsViewModel(application: Application): AndroidViewModel(application) {

    val getAllLists: LiveData<List<Lists>>
    private val repository: ListsRepository

    init {
        val listsDao = TodoDatabase.getDatabase(application).listDao()
        repository = ListsRepository(listsDao)
        getAllLists = repository.getAllLists

    }

    fun addLists(lists: Lists){
        viewModelScope.launch(Dispatchers.IO){
            repository.addLists(lists)
        }
    }

    fun updateLists(lists: Lists){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateLists(lists)
        }
    }

    fun deleteLists(lists: Lists){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteLists(lists)
        }
    }
}