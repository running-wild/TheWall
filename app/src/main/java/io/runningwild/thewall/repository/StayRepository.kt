package io.runningwild.thewall.repository

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.room.Room
import io.runningwild.thewall.database.AppDatabase
import io.runningwild.thewall.database.dao.StayDao
import io.runningwild.thewall.database.entity.StayEntity

class StayRepository internal constructor(context: Context) {

    private val mStayDao: StayDao
    private val allStays: LiveData<List<StayEntity>>

    init {
        val db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-name"
        ).build()

        mStayDao = db.stayDao()
        allStays = mStayDao.getAll()
    }

    fun insert(stay: StayEntity) {
        InsertAsyncTask(mStayDao).execute(stay)
    }

    private class InsertAsyncTask internal constructor(private val mAsyncTaskDao: StayDao) :
        AsyncTask<StayEntity, Void, Void>() {

        override fun doInBackground(vararg params: StayEntity): Void? {
            mAsyncTaskDao.insertAll(params[0])
            return null
        }
    }
}