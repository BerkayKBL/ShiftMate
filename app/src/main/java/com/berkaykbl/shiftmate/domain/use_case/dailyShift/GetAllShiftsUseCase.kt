package com.berkaykbl.shiftmate.domain.use_case.dailyShift

import com.berkaykbl.shiftmate.domain.mapper.toModel
import com.berkaykbl.shiftmate.domain.model.DailyShift
import com.berkaykbl.shiftmate.domain.repository.DailyShiftRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class GetAllShiftsUseCase(private val repository: DailyShiftRepository) {
    operator fun invoke(): Flow<List<DailyShift>> {
        return repository.getAllShifts().map {
            it.map {
                it.toModel()
            }
        }
    }
}