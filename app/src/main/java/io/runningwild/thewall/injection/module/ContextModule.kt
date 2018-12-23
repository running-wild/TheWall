package io.runningwild.thewall.injection.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule {
    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }
}