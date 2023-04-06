package com.example.todo_fefu.repository

import androidx.lifecycle.LiveData
import com.example.todo_fefu.data.task.Subtask
import com.example.todo_fefu.data.task.SubtaskDao
import com.example.todo_fefu.data.task.Task
import com.example.todo_fefu.data.task.TaskDao

class SubtaskRepository(private val subtaskDao: SubtaskDao) {

    fun getAllSubtasks(id: Int) : LiveData<List<Task>>{
        return subtaskDao.getAllSubtasks(id)
    }

    suspend fun addSubtask(subtask: Subtask) {
        subtaskDao.addSubtask(subtask)
    }

    suspend fun updateSubtask(subtask: Subtask) {
        subtaskDao.updateSubtask(subtask)
    }

    suspend fun deleteSubtask(subtask: Subtask) {
        subtaskDao.deleteSubtask(subtask)
    }

    suspend fun deleteSubtasksFromLists(list_id: Int) {
        subtaskDao.deleteSubtaskFromList(list_id)
    }
}