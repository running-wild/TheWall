package io.runningwild.thewall.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.runningwild.thewall.persistence.StayDao
import io.runningwild.thewall.viewmodel.StayViewModel

class ViewModelFactory(private val dataSource: StayDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StayViewModel::class.java)) {
            return StayViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}