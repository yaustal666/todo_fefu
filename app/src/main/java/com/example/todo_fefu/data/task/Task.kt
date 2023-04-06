package com.example.todo_fefu.data.task

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val list_id: Int,
    val title: String,
    val description: String,
    val date: String,
    val favorite: Boolean
): Parcelable