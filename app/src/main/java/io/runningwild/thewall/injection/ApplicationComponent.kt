package io.runningwild.thewall.injection

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import io.runningwild.thewall.MainApplication
import io.runningwild.thewall.injection.builder.ActivityBuilder
import javax.inject.Singleton

@Singleton
@Component(modules = [ActivityBuilder::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: MainApplication)
}
