package io.runningwild.thewall.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface StayDao {
    @Query("SELECT * FROM stay_table")
    fun getAll(): Flowable<List<Stay>>

    @Insert
    fun insert(stay: Stay)

    @Delete
    fun delete(stay: Stay)
}