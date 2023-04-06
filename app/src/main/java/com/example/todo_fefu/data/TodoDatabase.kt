package com.example.todo_fefu.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todo_fefu.data.task.Lists
import com.example.todo_fefu.data.task.ListsDao
import com.example.todo_fefu.data.task.Task
import com.example.todo_fefu.data.task.TaskDao

@Database(entities = [Task::class, Lists::class], version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    abstract fun listDao(): ListsDao

    companion object {

        @Volatile
        private var INSTANCE: TodoDatabase? = null

        fun getDatabase(context: Context): TodoDatabase {
            val tempInstance = INSTANCE

            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "todo_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}