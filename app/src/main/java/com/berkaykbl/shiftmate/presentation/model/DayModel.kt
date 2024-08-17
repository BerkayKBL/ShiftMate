package com.berkaykbl.shiftmate.presentation.model

data class DayModel(
    val text: String,
    val isCurrent: Boolean,
    val isSelected: Boolean,
    val clickable: Boolean,
    val shiftHour: Int,
    val shiftType : String = "",
    val nationalHoliday: String? = null,
)