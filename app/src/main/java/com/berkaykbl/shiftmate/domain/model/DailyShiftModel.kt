package com.berkaykbl.shiftmate.domain.model

data class DailyShift(

    val id: Int,
    val day: Int,
    val month: Int,
    val year: Int,
    val shiftType: String,
    val hour: Int
)