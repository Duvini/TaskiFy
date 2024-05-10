package com.example.taskify.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskify.R
import com.example.taskify.adapter.HabitsAdapter
import com.example.taskify.fragment.AddHabitsDialogFragment
import com.example.taskify.fragment.ManageHabitsDialogFragment
import com.example.taskify.util.HabitsViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HabitsActivity : AppCompatActivity() {

    private lateinit var mHabitsViewModel: HabitsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habits)

        val addHabitFAB = findViewById<FloatingActionButton>(R.id.fab_habits_add)

        // RecyclerView
        val habitsRV = findViewById<RecyclerView>(R.id.rv_habits)
        val habitsAdapter = HabitsAdapter()
        habitsRV.adapter = habitsAdapter
        habitsRV.layoutManager = LinearLayoutManager(this)


        mHabitsViewModel = ViewModelProvider(this).get(HabitsViewModel::class.java)
        mHabitsViewModel.allHabitsData.observe(this, Observer { habits ->
            habitsAdapter.setHabits(habits)
        })



        // habits card item onClick listener
        habitsAdapter.mHabitsItemClick = { habits ->
            val habitsManageDialogFragment = ManageHabitsDialogFragment()

            //Bundle to pass data
            val bundle = Bundle()
            bundle.putInt("Id", habits.id)
            bundle.putString("title", habits.cardTitle)
            bundle.putInt("daysGoal", habits.cardDayGoal)
            bundle.putInt("daysCompleted", habits.cardDaysCompleted)
            bundle.putLong("dateStored", habits.cardDateLastCompleted.time)
            bundle.putString("tag", habits.cardTag)

            habitsManageDialogFragment.arguments = bundle
            habitsManageDialogFragment.show(supportFragmentManager, "ManageDialog")
        }


        addHabitFAB.setOnClickListener {
            val habitsAddDialogFragment = AddHabitsDialogFragment()
            habitsAddDialogFragment.show(supportFragmentManager, "AddDialog")
        }

    }

}
