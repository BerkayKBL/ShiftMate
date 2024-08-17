package com.berkaykbl.shiftmate.presentation.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.berkaykbl.shiftmate.R
import com.berkaykbl.shiftmate.presentation.component.EntryDialog
import com.berkaykbl.shiftmate.presentation.menu.component.MenuItem
import com.berkaykbl.shiftmate.util.Screens
import com.berkaykbl.shiftmate.util.maxYear
import com.berkaykbl.shiftmate.util.minYear

@Composable
fun MenuScreen(navController: NavController) {

    val colorScheme = MaterialTheme.colorScheme
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.primary),
        containerColor = colorScheme.primary
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.Center,
            contentPadding = PaddingValues(15.dp, 0.dp)
        ) {
            item {
                MenuItem(R.drawable.vector_calendar, "Calendar") {
                    navController.navigate(Screens.Home.route)
                }
            }

            item {
                MenuItem(R.drawable.vector_money, "Salary") {
                    navController.navigate(Screens.SalaryMenu.route)
                }
            }
            item {
                MenuItem(R.drawable.vector_multiplier, "Multiplier") {

                }
            }
            item {
                MenuItem(R.drawable.vector_monthly, "Monthly Data") {

                }
            }
            item {
                MenuItem(R.drawable.vector_payments, "Bonuses") {

                }
            }
        }
    }
}