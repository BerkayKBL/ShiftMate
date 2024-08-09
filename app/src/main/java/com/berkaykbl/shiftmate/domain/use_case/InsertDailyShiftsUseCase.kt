package com.berkaykbl.shiftmate.domain.use_case

import com.berkaykbl.shiftmate.domain.model.DailyShift
import com.berkaykbl.shiftmate.domain.repository.DailyShiftRepository

class InsertDailyShiftsUseCase(
    private val repository: DailyShiftRepository
) {
    suspend operator fun invoke(dailyShift: DailyShift) = repository.insert(dailyShift)
}