package com.berkaykbl.shiftmate.domain.use_case.dailyShift

data class DailyShiftsUseCases (
    val getDailyShiftsForMonth: GetShiftsForMonthUseCase,
    val insertDailyShifts: InsertDailyShiftUseCase,
    val getAllShiftsUseCase: GetAllShiftsUseCase,
    val updateDailyShifts: UpdateDailyShiftUseCase,
    val getShiftForDay: GetShiftForDate,
    val deleteDailyShiftForDateUseCase: DeleteDailyShiftForDateUseCase
)