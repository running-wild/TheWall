package io.runningwild.thewall.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.runningwild.thewall.di.ViewModelFactory
import io.runningwild.thewall.di.ViewModelKey
import io.runningwild.thewall.viewmodel.StayViewModel

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(StayViewModel::class)
    abstract fun bindStayViewModel(viewModel: StayViewModel): ViewModel
}