package com.example.todo_fefu.data.task

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("DELETE FROM tasks WHERE list_id = :list_id")
    suspend fun deleteTasksFromList(list_id: Int)

    @Query("SELECT * FROM tasks ORDER BY id")
    fun getAllTasks(): LiveData<List<Task>>
}