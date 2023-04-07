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
    suspend fun deleteSubtaskFromTask(task_id: Int)

    @Query("DELETE FROM subtasks WHERE id IN (SELECT subtasks.id FROM tasks JOIN subtasks ON tasks.id = subtasks.task_id WHERE tasks.list_id = :list_id)")
    suspend fun deleteSubtaskFromList(list_id: Int)

    @Query("SELECT * FROM subtasks WHERE task_id = :task_id ORDER BY id")
    fun getAllSubtasks(task_id: Int): LiveData<List<Subtask>>
}