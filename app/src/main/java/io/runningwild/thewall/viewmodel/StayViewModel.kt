package io.runningwild.thewall.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.runningwild.thewall.persistence.Stay
import io.runningwild.thewall.persistence.StayDao

class StayViewModel(private val dao: StayDao) : ViewModel() {

    fun getAll(): Flowable<List<Stay>> {
        return dao.getAll()
    }

    fun insert(entryDate : String, leaveDate: String): Completable {
        return Completable.fromAction {
            val stay = Stay(0, entryDate, leaveDate)
            dao.insert(stay)
        }
    }
}