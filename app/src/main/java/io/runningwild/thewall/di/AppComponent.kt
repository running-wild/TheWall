package io.runningwild.thewall.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.runningwild.thewall.TheWallApplication
import io.runningwild.thewall.di.module.ActivityBuilderModule
import io.runningwild.thewall.di.module.AppModule
import io.runningwild.thewall.di.module.AppProviderModule
import io.runningwild.thewall.di.module.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilderModule::class,
        AppProviderModule::class,
        ViewModelModule::class
    )
)
internal interface AppComponent : AndroidInjector<TheWallApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<TheWallApplication>()
}