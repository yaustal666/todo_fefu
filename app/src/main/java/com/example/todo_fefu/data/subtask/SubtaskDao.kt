package com.example.todo_fefu.data.task

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SubtaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSubtask(subtask: Subtask)

    @Update
    suspend fun updateSubtask(subtask: Subtask)

    @Delete
    suspend fun deleteSubtask(subtask: Subtask)

    @Query("DELETE FROM subtasks WHERE task_id = :task_id")
    suspend fun deleteSubtaskFromList(task_id: Int)

    @Query("SELECT * FROM subtasks WHERE task_id = :task_id ORDER BY id")
    fun getAllSubtask(task_id: Int): LiveData<List<Task>>
}