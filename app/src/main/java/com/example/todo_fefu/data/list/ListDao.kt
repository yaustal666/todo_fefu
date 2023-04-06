package com.example.todo_fefu.data.task

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ListsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLists(lists: Lists)

    @Update
    suspend fun updateLists(lists: Lists)

    @Delete
    suspend fun deleteLists(lists: Lists)

    @Query("SELECT * FROM lists ORDER BY id")
    fun getAllLists(): LiveData<List<Lists>>
}