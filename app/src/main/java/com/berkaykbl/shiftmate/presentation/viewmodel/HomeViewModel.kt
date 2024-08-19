package com.berkaykbl.shiftmate.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.berkaykbl.shiftmate.domain.mapper.toDetailModel
import com.berkaykbl.shiftmate.domain.model.DailyShift
import com.berkaykbl.shiftmate.domain.use_case.dailyShift.DailyShiftsUseCases
import com.berkaykbl.shiftmate.domain.use_case.variable.VariableUseCases
import com.berkaykbl.shiftmate.presentation.model.MonthlyShiftDetailModel
import com.berkaykbl.shiftmate.presentation.model.VariableModel
import com.berkaykbl.shiftmate.util.calculateShiftDetail
import com.berkaykbl.shiftmate.util.nationalHolidays
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: DailyShiftsUseCases,
    private val variableUseCases: VariableUseCases
) : ViewModel() {

    private val _day = mutableIntStateOf(0)
    private val _month = mutableIntStateOf(0)
    private val _year = mutableIntStateOf(0)
    private val _currentCalendar = mutableStateOf<Calendar?>(null)
    private val _monthDays = mutableStateOf(emptyList<DailyShift>())
    private val _allDays = mutableStateOf(emptyList<DailyShift>())
    private val _dayDetails =
        mutableStateOf(
            MonthlyShiftDetailModel(
                0,
                0,
                0.0,
                0,
                0.0,
                0,
                0.0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0
            )
        )
    private val _variableModel = mutableStateOf(VariableModel(0, 0.0, 0.0, 0.0, 0.0, 0, 0))

    val day: State<Int> get() = _day
    val month: State<Int> get() = _month
    val year: State<Int> get() = _year
    val currentCalendar: State<Calendar?> get() = _currentCalendar
    val monthDays: State<List<DailyShift>> get() = _monthDays
    val allDays: State<List<DailyShift>> get() = _allDays
    val dayDetails: State<MonthlyShiftDetailModel> get() = _dayDetails
    val variableModel: State<VariableModel> get() = _variableModel


    init {
        getAllShifts()
        changeVariable("salary")
        changeVariable("multiplier")
        changeVariable("bonus")
        getShiftsForMonth()
    }

    fun setDailyShift(model: DailyShift) {
        viewModelScope.launch {
            val dailyShift = findShiftForDay(model.year, model.month, model.day)
            if (model.shiftType == "0" || ((model.shiftType == "overtime" || model.shiftType == "bonus") && model.hour == 0)) {
                useCases.deleteDailyShiftForDateUseCase(model.day, model.month, model.year)
            } else {
                if (dailyShift == null) {
                    useCases.insertDailyShifts.invoke(model)
                } else {
                    useCases.updateDailyShifts.invoke(model.copy(id = dailyShift.id))
                }
            }
        }
    }

    fun getAllShifts() {
        viewModelScope.launch {
            useCases.getAllShiftsUseCase.invoke().collect {
                _allDays.value = it
            }
        }
    }

    fun getShiftsForMonth() {
        viewModelScope.launch {
            useCases.getDailyShiftsForMonth.invoke(year.value, month.value).collect {
                _monthDays.value = it
                calculateDailyDetails()
            }
        }
    }

    private fun calculateDailyDetails() {
        val dayDetails = _monthDays.value.map {
            it.toDetailModel(
                nationalHolidays.contains("${it.day}/${it.month}")
            )
        }
        _dayDetails.value = calculateShiftDetail(dayDetails, variableModel.value)
    }

    fun calculateMonthlyDetail(
        dailyShifts: List<DailyShift>,
        variableModel: VariableModel
    ): MonthlyShiftDetailModel {
        var dailyShift = dailyShifts.map {
            it.toDetailModel(
                nationalHolidays.contains("${it.day}/${it.month}")
            )
        }
        return calculateShiftDetail(dailyShift, this.variableModel.value)
    }

    fun findShiftForDay(year: Int, month: Int, day: Int): DailyShift? {
        return _monthDays.value.find { it.day == day && it.year == year && it.month == month }
    }

    fun changeVariable(type: String, givenYear: String = "2024") {
        if (type == "salary") {
            viewModelScope.launch {
                variableUseCases.getVariableByKeyAndSubKeyUseCase.invoke(
                    "salary",
                    givenYear
                ).collect {
                    if (it.isNotEmpty()) {
                        _variableModel.value =
                            _variableModel.value.copy(salary = it.first().value.toInt())
                    }
                }
            }
        } else if (type == "multiplier") {

            viewModelScope.launch {
                variableUseCases.getVariablesByKeyUseCase.invoke(
                    "multiplier"
                ).collect {
                    val weekday = it.find { it.subKey == "weekday" }
                    val saturday = it.find { it.subKey == "saturday" }
                    val sunday = it.find { it.subKey == "sunday" }
                    val holiday = it.find { it.subKey == "holiday" }

                    _variableModel.value = _variableModel.value.copy(
                        weekdayMultiplier = weekday?.value?.toDouble() ?: 0.0,
                        saturdayMultiplier = saturday?.value?.toDouble() ?: 0.0,
                        sundayMultiplier = sunday?.value?.toDouble() ?: 0.0,
                        holidayMultiplier = holiday?.value?.toDouble() ?: 0.0
                    )
                }
            }
        } else if (type == "bonus") {
            viewModelScope.launch {
                variableUseCases.getVariablesByKeyUseCase.invoke(
                    "bonus"
                ).collect {

                    val attendanceBonus = it.find { it.subKey == "attendance" }
                    val extraBonus = it.find { it.subKey == "extra" }

                    _variableModel.value = _variableModel.value.copy(
                        attendanceBonus = attendanceBonus?.value?.toInt() ?: 0,
                        extraBonus = extraBonus?.value?.toInt() ?: 0,
                    )
                    calculateDailyDetails()
                }
            }
        }


    }


    fun changeDayValues(day: Int, month: Int, year: Int) {
        var newMonth = month
        var newYear = year
        if (newMonth < 1) {
            newMonth = 12
            newYear -= 1
        } else if (month > 12) {
            newMonth = 1
            newYear++
        }
        _day.intValue = day
        _month.intValue = newMonth
        _year.intValue = newYear
        changeVariable("salary")
        getShiftsForMonth()
    }

    fun setCurrentCalendar(calendar: Calendar) {
        _currentCalendar.value = calendar
    }

}