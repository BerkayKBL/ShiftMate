package com.berkaykbl.shiftmate.util

import com.berkaykbl.shiftmate.R

val nationalHolidays: Map<String, String> = mapOf(
    "1/1" to "Yılbaşı",
    "23/4" to "Ulusal Egemenlik ve Çocuk Bayramı",
    "1/5" to "İşçi Bayramı",
    "19/5" to "Atatürk'ü Anma ve Gençlik ve Spor Bayramı",
    "15/7" to "Demokrasi ve Birlik Günü",
    "30/8" to "Zafer Bayramı",
    "29/10" to "Cumhuriyet Bayramı"
)

val leaveTypes: Map<String, Int> = mapOf(
    "paid_leave" to R.string.leave_paid,
    "annual_leave" to R.string.leave_annual,
    "sick_leave" to R.string.leave_sick,
    "unpaid_leave" to R.string.leave_unpaid
)

val minYear = 1999
val maxYear = 2035