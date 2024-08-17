package com.berkaykbl.shiftmate.data.repository

import com.berkaykbl.shiftmate.data.entity.DailyShiftEntity
import com.berkaykbl.shiftmate.data.local.DailyShiftDao
import com.berkaykbl.shiftmate.domain.mapper.toEntity
import com.berkaykbl.shiftmate.domain.mapper.toModel
import com.berkaykbl.shiftmate.domain.model.DailyShift
import com.berkaykbl.shiftmate.domain.repository.DailyShiftRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class DailyShiftRepositoryImpl(private val dailyShiftDao: DailyShiftDao) : DailyShiftRepository {
    /*

    override fun getShiftsForDate(year: Int, month: Int, day: Int): List<DailyShift> {
        return dailyShiftDao.getShiftsForDate(year, month, day).map { it.toModel() }
    }*/

    override fun getShiftsForMonth(year: Int, month: Int): Flow<List<DailyShiftEntity>> {
        return dailyShiftDao.getShiftsForMonth(year, month)
    }

    override fun getShiftForDate(year: Int, month: Int, day: Int): Flow<List<DailyShiftEntity>> {
        return dailyShiftDao.getShiftForDate(year, month, day)
    }

    override suspend fun insert(dailyShift: DailyShift) {

        dailyShiftDao.insert(dailyShift.toEntity())
    }

    override suspend fun update(dailyShift: DailyShift) {
        dailyShiftDao.update(dailyShift.toEntity())
    }

    override suspend fun deleteShiftForDate(day: Int, month: Int, year: Int) {
        dailyShiftDao.deleteShiftForDate(day, month, year)
    }


    /*override fun deleteShiftByMonth(year: Int, month: Int) {
        dailyShiftDao.deleteShiftByMonth(year, month)
    }



    override fun update(dailyShift: DailyShift) {
        dailyShiftDao.update(dailyShift.toEntity())
    }*/
}