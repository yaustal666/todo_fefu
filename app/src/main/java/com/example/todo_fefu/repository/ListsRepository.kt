package com.example.todo_fefu.repository

import androidx.lifecycle.LiveData
import com.example.todo_fefu.data.task.Lists
import com.example.todo_fefu.data.task.ListsDao

class ListsRepository(private val listsDao: ListsDao) {

    val getAllLists: LiveData<List<Lists>> = listsDao.getAllLists()

    suspend fun addLists(lists: Lists){
        listsDao.addLists(lists)
    }

    suspend fun updateLists(lists: Lists){
        listsDao.updateLists(lists)
    }

    suspend fun deleteLists(lists: Lists){
        listsDao.deleteLists(lists)
    }

}