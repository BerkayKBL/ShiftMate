package com.berkaykbl.shiftmate.domain.repository

import com.berkaykbl.shiftmate.data.entity.DailyShiftEntity
import com.berkaykbl.shiftmate.domain.model.DailyShift

interface DailyShiftRepository {

    suspend fun getShiftsForMonth(year: Int, month: Int): List<DailyShift>
    suspend fun getShiftsForDate(year: Int, month: Int, day: Int): List<DailyShift>
    suspend fun insert(dailyShift: DailyShift)
    suspend fun deleteShiftByMonth(year: Int, month: Int)
    suspend fun deleteShiftByDate(year: Int, month: Int, day: Int)
    suspend fun update(dailyShift: DailyShift)
}