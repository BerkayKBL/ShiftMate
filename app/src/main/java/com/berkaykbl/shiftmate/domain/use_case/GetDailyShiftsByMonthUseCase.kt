package com.berkaykbl.shiftmate.domain.use_case

import com.berkaykbl.shiftmate.domain.model.DailyShift
import com.berkaykbl.shiftmate.domain.repository.DailyShiftRepository

class GetDailyShiftsByMonthUseCase(
    private val repository: DailyShiftRepository
) {
    suspend operator fun invoke(year: Int, month: Int): List<DailyShift> {
        return repository.getShiftsForMonth(year, month)
    }
}