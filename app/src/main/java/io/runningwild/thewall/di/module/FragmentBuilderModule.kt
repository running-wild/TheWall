package io.runningwild.thewall.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.runningwild.thewall.view.InputFragment

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeInputFragment(): InputFragment
}