package io.runningwild.thewall.di.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.runningwild.thewall.persistence.AppDatabase
import io.runningwild.thewall.persistence.dao.StayDao
import javax.inject.Singleton

@Module
class AppProviderModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, "the-wall.db").build()

    @Provides
    @Singleton
    fun provideStayDao(database: AppDatabase): StayDao =
        database.stayDao()
}