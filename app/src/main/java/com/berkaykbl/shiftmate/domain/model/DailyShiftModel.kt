package com.berkaykbl.shiftmate.domain.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class DailyShift(

    val id: Int,
    val year: Int,
    val month: Int,
    val day: Int,
    val shiftType: String,
    val hour: Int
)