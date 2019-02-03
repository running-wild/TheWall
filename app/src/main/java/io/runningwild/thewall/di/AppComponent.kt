package io.runningwild.thewall.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import io.runningwild.thewall.TheWallApplication
import io.runningwild.thewall.di.module.AndroidBindingModule
import io.runningwild.thewall.di.module.AppModule
import io.runningwild.thewall.di.module.AppProviderModule
import io.runningwild.thewall.di.module.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        AndroidInjectionModule::class,
        AppModule::class,
        AppProviderModule::class,
        AndroidBindingModule::class,
        ViewModelModule::class
    )
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: TheWallApplication): Builder

        fun build(): AppComponent
    }

    fun inject(app: TheWallApplication)
}
