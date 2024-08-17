package com.berkaykbl.shiftmate.domain.use_case.dailyShift

import com.berkaykbl.shiftmate.domain.mapper.toModel
import com.berkaykbl.shiftmate.domain.model.DailyShift
import com.berkaykbl.shiftmate.domain.repository.DailyShiftRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetShiftForDate(
    private val repository: DailyShiftRepository
) {
    operator fun invoke(year: Int, month: Int, day: Int): Flow<List<DailyShift>> {
        return repository.getShiftForDate(year, month, day).map {
            it.map {
                it.toModel()
            }
        }

    }
}