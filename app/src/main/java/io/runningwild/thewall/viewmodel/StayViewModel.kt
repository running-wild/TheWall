package io.runningwild.thewall.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.runningwild.thewall.persistence.model.Stay
import io.runningwild.thewall.persistence.repository.StayRepository
import javax.inject.Inject

class StayViewModel @Inject constructor(private val repository: StayRepository) : ViewModel() {

    fun getAll(): Flowable<List<Stay>> {
        return repository.getAll()
    }

    fun insert(entryDate : String, leaveDate: String): Completable {
        return repository.insert(entryDate, leaveDate)
    }
}