package io.runningwild.thewall.persistence

import androidx.room.*
import io.reactivex.Observable

@Dao
interface StayDao {
    @Query("SELECT * FROM stay_table")
    fun getAll(): Observable<List<Stay>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(stay: Stay)

    @Delete
    fun delete(stay: Stay)
}