package com.berkaykbl.shiftmate.presentation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Menu: Screen("menu")
}