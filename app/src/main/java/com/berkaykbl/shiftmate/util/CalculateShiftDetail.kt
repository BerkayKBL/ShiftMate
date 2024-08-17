package com.berkaykbl.shiftmate.util

import com.berkaykbl.shiftmate.presentation.model.DailyDetailModel
import com.berkaykbl.shiftmate.presentation.model.MonthlyShiftDetailModel
import com.berkaykbl.shiftmate.presentation.model.VariableModel

import java.util.Calendar

fun calculateShiftDetail(
    days: List<DailyDetailModel>,
    variableModel: VariableModel
): MonthlyShiftDetailModel {

    val model = variableModel
    val cal = Calendar.getInstance()

    val attendanceBonus = model.attendanceBonus

    val baseSalary = model.salary
    val dailySalary = baseSalary / 30
    val hourlySalary = dailySalary / 7.5

    val weekdayMultiplier = model.weekdayMultiplier
    val saturdayMultiplier = model.saturdayMultiplier
    val sundayMultiplier = model.sundayMultiplier
    val holidayMultiplier = model.holidayMultiplier

    var totalSalary = baseSalary.toDouble()
    var overtimePay = 0.0
    var bonuses = 0
    var leaveDeductions = 0.0

    var totalOvertime = 0
    var weekdayOvertime = 0
    var saturdayOvertime = 0
    var sundayOvertime = 0
    var holidayOvertime = 0

    var totalLeave = 0
    var deductedDay = 0
    var paidLeave = 0
    var unpaidLeave = 0
    var sickLeave = 0
    var annualLeave = 0

    var weekOfMonth = -1

    days.forEach { dayModel ->
        val shiftType = dayModel.shiftType
        cal.set(dayModel.year, dayModel.month - 1, dayModel.day)
        val dayOfWeekIndex = cal.get(Calendar.DAY_OF_WEEK) - 1
        val nowWeekOfMonth = cal.get(Calendar.WEEK_OF_MONTH)
        val dayOfWeek = when (dayOfWeekIndex) {
            in 1..5 -> 1
            6 -> 2
            else -> 3
        }
        val hour = dayModel.hour
        val isNationalHoliday = dayModel.isNationalHoliday

        if (shiftType.isEmpty()) return@forEach
        if (shiftType == "overtime") {
            if (dayOfWeek == 1) {
                if (isNationalHoliday) {
                    holidayOvertime += hour
                } else {
                    weekdayOvertime += hour
                }
            } else if (dayOfWeek == 2) {
                saturdayOvertime += hour
            } else {
                sundayOvertime += hour
            }
            totalOvertime += hour
        } else if (shiftType == "bonus") {
            bonuses += hour
        } else {
            if (dayOfWeek == 1) {
                if (shiftType == "paid_leave") {
                    paidLeave++
                } else if (shiftType == "annual_leave") {
                    annualLeave++
                } else {
                    if (shiftType == "sick_leave") {
                        sickLeave++
                    } else {
                        unpaidLeave++
                    }
                    deductedDay++
                    if (weekOfMonth != nowWeekOfMonth) {
                        deductedDay++
                    }
                    weekOfMonth = cal.get(Calendar.WEEK_OF_MONTH)
                }
                totalLeave++

            }
        }
    }

    overtimePay += (weekdayOvertime * weekdayMultiplier)
    overtimePay += (saturdayOvertime * saturdayMultiplier)
    overtimePay += (sundayOvertime * sundayMultiplier)
    overtimePay += (holidayOvertime * holidayMultiplier)
    overtimePay *= hourlySalary


    leaveDeductions = deductedDay.toDouble() * dailySalary
    if (leaveDeductions == 0.0) {
        bonuses += attendanceBonus
    }


    totalSalary += overtimePay + bonuses - leaveDeductions


    return MonthlyShiftDetailModel(
        totalSalary.toTwoDigitDouble(),
        baseSalary,
        overtimePay.toTwoDigitDouble(),
        bonuses,
        leaveDeductions.toTwoDigitDouble(),
        totalOvertime,
        weekdayOvertime,
        saturdayOvertime,
        sundayOvertime,
        holidayOvertime,
        totalLeave,
        deductedDay,
        paidLeave,
        unpaidLeave,
        sickLeave,
        annualLeave
    )
}