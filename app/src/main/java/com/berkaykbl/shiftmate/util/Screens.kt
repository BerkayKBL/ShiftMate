package com.berkaykbl.shiftmate.util

sealed class Screens(val route: String) {
    object Home : Screens("home")
    object Menu: Screens("menu")
    object SalaryMenu : Screens("menu.salary")
    object MultiplierMenu: Screens("menu.multiplier")
    object BonusMenu: Screens("menu.bonus")
    object MonthlyDataMenu: Screens("menu.monthlyData")
}