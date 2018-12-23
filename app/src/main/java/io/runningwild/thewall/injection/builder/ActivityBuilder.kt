package io.runningwild.thewall.injection.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.runningwild.thewall.view.MainActivity

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun contributeMainActivityInjector(): MainActivity
}