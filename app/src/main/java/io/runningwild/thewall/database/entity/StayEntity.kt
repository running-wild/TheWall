package io.runningwild.thewall.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stay_table")
data class StayEntity(
    @PrimaryKey var uid: Int,
    @ColumnInfo(name = "enter_date") var enterDate: String?,
    @ColumnInfo(name = "leave_date") var leaveDate: String?
)