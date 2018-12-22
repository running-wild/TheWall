package io.runningwild.thewall.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.runningwild.thewall.database.entity.StayEntity

@Dao
interface StayDao {
    @Query("SELECT * FROM stay_table")
    fun getAll(): LiveData<List<StayEntity>>

    @Insert
    fun insertAll(vararg stay: StayEntity)

    @Delete
    fun delete(stay: StayEntity)
}