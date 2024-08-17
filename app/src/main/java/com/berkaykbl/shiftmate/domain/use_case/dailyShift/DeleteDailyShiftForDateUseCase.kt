package com.berkaykbl.shiftmate.domain.use_case.dailyShift

import com.berkaykbl.shiftmate.domain.repository.DailyShiftRepository

class DeleteDailyShiftForDateUseCase(
    private val repository: DailyShiftRepository
) {
    suspend operator fun invoke(day: Int, month: Int, year: Int) = repository.deleteShiftForDate(day, month, year)
}