package com.example.taskify.util


import android.app.Application
import androidx.lifecycle.*
import com.example.taskify.data.Habits
import com.example.taskify.data.MyDatabase
import com.example.taskify.data.HabitsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HabitsViewModel(application: Application):AndroidViewModel(application) {

    val allHabitsData: LiveData<List<Habits>>
    private val repository: HabitsRepository

    init {
        val habitsDao = MyDatabase.getDatabase(application).habitsDao()
        repository = HabitsRepository(habitsDao)
        allHabitsData = repository.readAllHabitsData
    }

    fun addHabit(habits: Habits) = viewModelScope.launch(Dispatchers.IO){
        repository.addHabitData(habits)
    }

    fun updateHabit(habits: Habits) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateHabitData(habits)
    }

    fun deleteHabit(habits: Habits) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteHabitData(habits)
    }

}
