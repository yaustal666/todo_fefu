package com.example.todo_fefu.data.task

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "subtasks")
data class Subtask(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val task_id: Int,
    val title: String,
    val description: String,
    val date: String
): Parcelable