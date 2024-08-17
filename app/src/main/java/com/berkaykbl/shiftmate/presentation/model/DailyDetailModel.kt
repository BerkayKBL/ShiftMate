package com.berkaykbl.shiftmate.presentation.model

data class DailyDetailModel(
    val day: Int,
    val month: Int,
    val year: Int,
    val shiftType: String,
    val hour: Int,
    val isNationalHoliday: Boolean
)