package io.runningwild.thewall.persistence.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import io.runningwild.thewall.persistence.dao.StayDao
import io.runningwild.thewall.persistence.model.Stay
import javax.inject.Inject

class StayRepository @Inject constructor(private val dao: StayDao) {

    fun getAll(): Flowable<List<Stay>> = dao.getAll()

    fun insert(entryDate: String, leaveDate: String) : Completable {
        return Completable.fromAction {
            dao.insert(Stay(0, entryDate, leaveDate))
        }
    }
}