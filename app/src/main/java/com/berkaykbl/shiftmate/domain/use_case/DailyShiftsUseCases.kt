package com.berkaykbl.shiftmate.domain.use_case

data class DailyShiftsUseCases (
    val getDailyShiftsByMonth: GetDailyShiftsByMonthUseCase,
    val insertDailyShifts: InsertDailyShiftsUseCase,
)