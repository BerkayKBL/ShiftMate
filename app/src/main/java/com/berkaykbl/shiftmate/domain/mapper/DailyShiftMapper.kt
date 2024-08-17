package com.berkaykbl.shiftmate.domain.mapper

import com.berkaykbl.shiftmate.data.entity.DailyShiftEntity
import com.berkaykbl.shiftmate.domain.model.DailyShift
import com.berkaykbl.shiftmate.presentation.model.DailyDetailModel


fun DailyShiftEntity.toModel() : DailyShift {
    return DailyShift(
        id = id,
        year = year,
        month = month,
        day = day,
        shiftType = shiftType,
        hour = hour
    )
}

fun DailyShift.toEntity() : DailyShiftEntity {
    return DailyShiftEntity(
        id = id,
        year = year,
        month = month,
        day = day,
        shiftType = shiftType,
        hour = hour
    )
}

fun DailyShift.toDetailModel(isNationalHoliday: Boolean) : DailyDetailModel {
    return DailyDetailModel(
        day,
        month,
        year,
        shiftType,
        hour,
        isNationalHoliday
    )
}