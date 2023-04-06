package com.example.todo_fefu.repository

import androidx.lifecycle.LiveData
import com.example.todo_fefu.data.task.Task
import com.example.todo_fefu.data.task.TaskDao

class TaskRepository(private val taskDao: TaskDao) {

    fun getAllTasks(id: Int) : LiveData<List<Task>>{
        return taskDao.getAllTasks(id)
    }

    suspend fun addTask(task: Task) {
        taskDao.addTask(task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    suspend fun deleteTasksFromLists(list_id: Int) {
        taskDao.deleteTasksFromList(list_id)
    }
}