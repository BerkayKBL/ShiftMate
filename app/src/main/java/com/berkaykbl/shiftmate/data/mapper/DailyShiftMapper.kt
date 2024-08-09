package com.berkaykbl.shiftmate.data.mapper

import com.berkaykbl.shiftmate.data.entity.DailyShiftEntity
import com.berkaykbl.shiftmate.domain.model.DailyShift


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