package io.runningwild.thewall.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.runningwild.thewall.view.MainActivity

@Module
abstract class AndroidBindingModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}