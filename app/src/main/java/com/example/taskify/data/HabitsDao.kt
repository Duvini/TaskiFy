package com.example.taskify.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.taskify.data.Habits

@Dao
interface HabitsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addHabit(habits: Habits)

    @Update
    fun updateHabit(habits: Habits)

    @Delete
    fun deleteHabit(habits: Habits)

    @Query("SELECT * FROM table_habits ORDER BY id ASC")
    fun readAllHabits() : LiveData<List<Habits>>
}