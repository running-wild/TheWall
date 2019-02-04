package io.runningwild.thewall.persistence

import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class StayRepository @Inject constructor(private val dao: StayDao) {

    fun getAll(): Observable<List<Stay>> = dao.getAll()

    fun insert(entryDate: String, leaveDate: String) : Completable {
        return Completable.fromAction {
            dao.insert(Stay(null, entryDate, leaveDate))
        }
    }
}