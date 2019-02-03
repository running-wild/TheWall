package io.runningwild.thewall.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stay_table")
data class Stay(
    @PrimaryKey var uid: Int,
    @ColumnInfo(name = "entry_date") var entryDate: String?,
    @ColumnInfo(name = "leave_date") var leaveDate: String?
)