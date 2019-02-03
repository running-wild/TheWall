package io.runningwild.thewall

import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.runningwild.thewall.di.DaggerAppComponent
import timber.log.Timber

class TheWallApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Stetho.initializeWithDefaults(this)
    }

    override fun applicationInjector(): AndroidInjector<out TheWallApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}