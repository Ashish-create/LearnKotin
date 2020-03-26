package com.universal.best.habittracker.repository.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.universal.best.habittracker.repository.Dao.AcitivityClassDao
import com.universal.best.habittracker.repository.entity.ActivityEntityClass

@Database(entities = arrayOf(ActivityEntityClass::class), version = 1, exportSchema = false)
 abstract class AcitivityDataBase : RoomDatabase() {

    abstract fun getDao(): AcitivityClassDao

    companion object {

        @Volatile
        private var instance: AcitivityDataBase? = null;

        fun getdataBaseInstance(context: Context): AcitivityDataBase {
            val tempInstance =
                instance
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AcitivityDataBase::class.java,
                    "habitdatabase"
                ).build()
                Companion.instance = instance
                return instance
            }

        }


    }




}


