package com.berkaykbl.shiftmate.presentation.model

data class MonthlyShiftDetailModel (


    // DATE
    val month: Int,
    val year: Int,

    // SALARY DETAILS
    val totalSalary: Double,
    val baseSalary: Int,
    val overtimePay: Double,
    val bonuses: Int,
    val leaveDeductions: Double,

    // OVERTIME DETAILS
    val totalOvertime: Int,
    val weekdayOvertime: Int,
    val saturdayOvertime: Int,
    val sundayOvertime: Int,
    val holidayOvertime: Int,

    // LEAVE DETAILS
    val totalLeave: Int,
    val deductedDay: Int,
    val paidLeave: Int,
    val unpaidLeave: Int,
    val sickLeave: Int,
    val annualLeave: Int,
)