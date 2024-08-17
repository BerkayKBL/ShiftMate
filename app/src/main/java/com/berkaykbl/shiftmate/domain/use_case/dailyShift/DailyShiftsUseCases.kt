package com.berkaykbl.shiftmate.domain.use_case.dailyShift

data class DailyShiftsUseCases (
    val getDailyShiftsForMonth: GetShiftsForMonthUseCase,
    val insertDailyShifts: InsertDailyShiftUseCase,
    val updateDailyShifts: UpdateDailyShiftUseCase,
    val getShiftForDay: GetShiftForDate,
    val deleteDailyShiftForDateUseCase: DeleteDailyShiftForDateUseCase
)