package com.berkaykbl.shiftmate.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val _days = mutableStateOf(emptyList<DailyShift>())
    private val _dayDetails =
        mutableStateOf(
            MonthlyShiftDetailModel(
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
    private val _variableModel = mutableStateOf(VariableModel(0, 0.0, 0.0, 0.0, 0.0, 0))

    val day: State<Int> get() = _day
    val month: State<Int> get() = _month
    val year: State<Int> get() = _year
    val currentCalendar: State<Calendar?> get() = _currentCalendar
    val days: State<List<DailyShift>> get() = _days
    val dayDetails: State<MonthlyShiftDetailModel> get() = _dayDetails
    private val variableModel: State<VariableModel> get() = _variableModel


    init {
        getShiftsForMonth()
        changeSalary()
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

    private fun getShiftsForMonth() {
        viewModelScope.launch {
            useCases.getDailyShiftsForMonth.invoke(year.value, month.value).collect {
                _days.value = it
                calculateDailyDetails()
            }
        }

    }

    private fun calculateDailyDetails() {
        val dayDetails = days.value.map {
            it.toDetailModel(
                nationalHolidays.contains("${it.day}/${it.month}")
            )
        }
        _dayDetails.value = calculateShiftDetail(dayDetails, variableModel.value)
    }

    fun findShiftForDay(year: Int, month: Int, day: Int): DailyShift? {
        return days.value.find { it.day == day && it.year == year && it.month == month }
    }

    fun changeSalary() {
        viewModelScope.launch {
            variableUseCases.getVariableByKeyAndSubKeyUseCase.invoke(
                "salary",
                year.value.toString()
            ).collect {
                println(it.size)
                if (it.size > 0) {
                    _variableModel.value = _variableModel.value.copy(salary = it.first().value.toInt())
                    calculateDailyDetails()
                }
            }
        }
    }


    fun changeDayValues(day: Int, month: Int, year: Int, variableModel: VariableModel? = null) {
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
        changeSalary()
        getShiftsForMonth()
    }

    fun setCurrentCalendar(calendar: Calendar) {
        _currentCalendar.value = calendar
    }

}