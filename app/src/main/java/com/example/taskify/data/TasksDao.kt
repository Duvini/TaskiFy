package com.example.taskify.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TasksDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTask(tasks: Tasks)

    @Update
    fun updateTask(tasks: Tasks)

    @Delete
    fun deleteTask(tasks: Tasks)

    @Query("SELECT * FROM table_tasks")
    fun readAllTasks() : LiveData<List<Tasks>>

    @Query("SELECT COUNT(*) FROM table_tasks")
    fun getTasksCount(): LiveData<Int>

}