package com.berkaykbl.shiftmate.domain.use_case.dailyShift

import com.berkaykbl.shiftmate.domain.mapper.toModel
import com.berkaykbl.shiftmate.domain.model.DailyShift
import com.berkaykbl.shiftmate.domain.repository.DailyShiftRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetShiftsForMonthUseCase(
    private val repository: DailyShiftRepository
) {
    operator fun invoke(year: Int, month: Int): Flow<List<DailyShift>> {
        return repository.getShiftsForMonth(year, month).map {
            it.map {
                it.toModel()
            }
        }
    }
}