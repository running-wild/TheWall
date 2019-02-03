package io.runningwild.thewall.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.runningwild.thewall.view.MainActivity

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [(FragmentBuilderModule::class)])
    abstract fun contributeMainActivity(): MainActivity
}