package com.berkaykbl.shiftmate.presentation.model

data class VariableModel (
    val salary : Int,

    val weekdayMultiplier: Double,
    val saturdayMultiplier: Double,
    val sundayMultiplier: Double,
    val holidayMultiplier: Double,

    val attendanceBonus: Int,

)