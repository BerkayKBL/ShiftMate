package com.berkaykbl.shiftmate.domain.use_case.dailyShift

import com.berkaykbl.shiftmate.domain.model.DailyShift
import com.berkaykbl.shiftmate.domain.repository.DailyShiftRepository

class UpdateDailyShiftUseCase(
    private val repository: DailyShiftRepository
) {
    suspend operator fun invoke(dailyShift: DailyShift) = repository.update(dailyShift)
}