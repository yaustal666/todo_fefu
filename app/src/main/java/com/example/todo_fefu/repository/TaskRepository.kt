package com.example.todo_fefu.repository

import androidx.lifecycle.LiveData
import com.example.todo_fefu.data.task.Task
import com.example.todo_fefu.data.task.TaskDao

class TaskRepository(private val taskDao: TaskDao) {

    val getAllTasks: LiveData<List<Task>> = taskDao.getAllTasks()

    suspend fun addTask(task: Task) {
        taskDao.addTask(task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }
}