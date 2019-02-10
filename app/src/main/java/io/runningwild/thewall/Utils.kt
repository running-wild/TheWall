package io.runningwild.thewall

import android.content.Context
import android.text.format.DateFormat
import io.runningwild.thewall.persistence.Stay
import java.util.*
import java.util.Calendar.*

object Utils {

    private const val MILLISECONDS_IN_AN_HOUR = 86400000.0

    fun getDayCount(context: Context, stay: Stay): Int {
        val dateFormat = DateFormat.getDateFormat(context)

        val slidingWindowStart = getInstance()
        slidingWindowStart.set(HOUR_OF_DAY, 0)
        slidingWindowStart.set(MINUTE, 0)
        slidingWindowStart.set(SECOND, 0)
        slidingWindowStart.set(MILLISECOND, 0)
        slidingWindowStart.add(YEAR, -1)

        val entryDate: Calendar = getInstance()
        entryDate.time = dateFormat.parse(stay.entryDate)

        val leaveDate: Calendar = getInstance()
        leaveDate.time = dateFormat.parse(stay.leaveDate)

        if (leaveDate.timeInMillis < slidingWindowStart.timeInMillis) {
            return 0
        }
        if (entryDate.timeInMillis < slidingWindowStart.timeInMillis) {
            entryDate.timeInMillis = slidingWindowStart.timeInMillis
        }
        return Math.ceil(((leaveDate.timeInMillis - entryDate.timeInMillis)
                / MILLISECONDS_IN_AN_HOUR)).toInt() + 1
    }
}