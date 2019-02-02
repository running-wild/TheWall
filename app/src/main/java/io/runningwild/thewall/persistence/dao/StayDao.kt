package io.runningwild.thewall.persistence.dao

import androidx.room.*
import io.reactivex.Flowable
import io.runningwild.thewall.persistence.model.Stay

@Dao
interface StayDao {
    @Query("SELECT * FROM stay_table")
    fun getAll(): Flowable<List<Stay>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(stay: Stay)

    @Delete
    fun delete(stay: Stay)
}