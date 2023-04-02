package com.example.todo_fefu.data.task

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val list_id: Int,
    val title: String,
    val description: String,
    val date: String,
    val favorite: Boolean
)