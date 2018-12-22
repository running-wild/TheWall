package io.runningwild.thewall

import android.content.Context
import io.runningwild.thewall.persistence.AppDatabase
import io.runningwild.thewall.persistence.StayDao
import io.runningwild.thewall.ui.ViewModelFactory

object Injection {

    fun provideStayDao(context: Context): StayDao {
        val database = AppDatabase.getInstance(context)
        return database.stayDao()
    }

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val dataSource = provideStayDao(context)
        return ViewModelFactory(dataSource)
    }
}