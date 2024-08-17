package com.berkaykbl.shiftmate.domain.repository

import com.berkaykbl.shiftmate.data.entity.DailyShiftEntity
import com.berkaykbl.shiftmate.domain.model.DailyShift
import kotlinx.coroutines.flow.Flow

interface DailyShiftRepository {

    suspend fun insert(dailyShift: DailyShift)
    suspend fun update(dailyShift: DailyShift)
    fun getShiftsForMonth(year: Int, month: Int): Flow<List<DailyShiftEntity>>
    fun getShiftForDate(year: Int, month: Int, day: Int): Flow<List<DailyShiftEntity>>
    suspend fun deleteShiftForDate(year: Int, month: Int, day: Int)
    /*
    fun getShiftsForDate(year: Int, month: Int, day: Int): List<DailyShift>
    fun deleteShiftByMonth(year: Int, month: Int)
    fun update(dailyShift: DailyShift)*/
}