package io.runningwild.thewall

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.runningwild.thewall.di.DaggerAppComponent


class TheWallApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out TheWallApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}