package io.runningwild.thewall.injection.builder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.runningwild.thewall.injection.module.ViewModelFactory
import io.runningwild.thewall.injection.module.ViewModelKey
import io.runningwild.thewall.viewmodel.StayViewModel

@Module
abstract class ViewModelBuilder {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(StayViewModel::class)
    abstract fun stayViewModel(viewModel: StayViewModel): ViewModel
}