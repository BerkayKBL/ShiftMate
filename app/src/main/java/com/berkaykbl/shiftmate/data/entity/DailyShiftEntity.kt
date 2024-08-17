package com.berkaykbl.shiftmate.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "shifts",
)
data class DailyShiftEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "year")
    val year: Int,
    @ColumnInfo(name = "month")
    val month: Int,
    @ColumnInfo(name = "day")
    val day: Int,
    @ColumnInfo(name = "shift_type")
    val shiftType: String,
    @ColumnInfo(name = "hour")
    val hour: Int
)