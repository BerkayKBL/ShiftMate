package com.berkaykbl.shiftmate.util

import android.icu.text.DecimalFormat
import java.math.RoundingMode

const val format = "#,###.##"

fun Double.toTwoDigitDouble() =
    this.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN).toDouble()