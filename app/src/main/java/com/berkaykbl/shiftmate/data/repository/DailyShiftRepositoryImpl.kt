package com.berkaykbl.shiftmate.data.repository

import com.berkaykbl.shiftmate.data.local.DailyShiftDao
import com.berkaykbl.shiftmate.data.mapper.toEntity
import com.berkaykbl.shiftmate.data.mapper.toModel
import com.berkaykbl.shiftmate.domain.model.DailyShift
import com.berkaykbl.shiftmate.domain.repository.DailyShiftRepository

class DailyShiftRepositoryImpl(private val dailyShiftDao: DailyShiftDao) : DailyShiftRepository {
    override suspend fun getShiftsForMonth(year: Int, month: Int): List<DailyShift> {
        return dailyShiftDao.getShiftsForMonth(year, month).map { it.toModel() }
    }

    override suspend fun getShiftsForDate(year: Int, month: Int, day: Int): List<DailyShift> {
        return dailyShiftDao.getShiftsForDate(year, month, day).map { it.toModel() }
    }

    override suspend fun insert(dailyShift: DailyShift) {
        dailyShiftDao.insert(dailyShift.toEntity())
    }

    override suspend fun deleteShiftByMonth(year: Int, month: Int) {
        dailyShiftDao.deleteShiftByMonth(year, month)
    }

    override suspend fun deleteShiftByDate(year: Int, month: Int, day: Int) {
        dailyShiftDao.deleteShiftByDate(year, month, day)
    }

    override suspend fun update(dailyShift: DailyShift) {
        dailyShiftDao.update(dailyShift.toEntity())
    }
}