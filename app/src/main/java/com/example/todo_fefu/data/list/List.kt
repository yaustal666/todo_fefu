package com.example.todo_fefu.data.task

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "lists")
data class Lists(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String
): Parcelable