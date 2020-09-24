package com.enping.cccandroidtest.repository.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.enping.cccandroidtest.model.Estimate
import com.enping.cccandroidtest.model.Person

@Database(entities = [Estimate::class, Person::class], version = 1, exportSchema = false)
abstract class RecordDatabase : RoomDatabase() {

    abstract fun estimateDao(): EstimateDao
    abstract fun personDao(): PersonDao

    companion object {
        @Volatile
        private var INSTANCE: RecordDatabase? = null

        fun getDatabase(context: Context): RecordDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecordDatabase::class.java,
                    "local_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}