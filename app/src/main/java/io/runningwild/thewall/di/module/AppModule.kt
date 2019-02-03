package io.runningwild.thewall.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.runningwild.thewall.TheWallApplication
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideApplication(application: TheWallApplication): Application = application
}