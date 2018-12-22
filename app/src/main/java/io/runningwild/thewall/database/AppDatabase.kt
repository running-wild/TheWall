package io.runningwild.thewall.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.runningwild.thewall.database.dao.StayDao
import io.runningwild.thewall.database.entity.StayEntity

@Database(entities = arrayOf(StayEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun stayDao(): StayDao
}