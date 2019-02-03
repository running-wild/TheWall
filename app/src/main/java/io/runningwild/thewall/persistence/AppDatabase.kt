package io.runningwild.thewall.persistence

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Stay::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun stayDao(): StayDao
}