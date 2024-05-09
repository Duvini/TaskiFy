package com.example.data

import android.content.Context
import androidx.room.*
import com.example.util.DateTypeConverter

@Database(entities = [ Tasks::class], version = 1, exportSchema = false)
@TypeConverters(DateTypeConverter::class)
abstract class MyDatabase : RoomDatabase() {


    abstract fun tasksDao() : TasksDao

    companion object {

        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "database_tasks_habits"
                ).build()
                INSTANCE = instance
                return instance
            }

        }


    }

}