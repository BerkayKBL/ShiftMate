package com.berkaykbl.shiftmate.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.berkaykbl.shiftmate.presentation.home.component.Day
import com.berkaykbl.shiftmate.presentation.model.DayModel
import com.berkaykbl.shiftmate.presentation.model.VariableModel
import com.berkaykbl.shiftmate.presentation.viewmodel.HomeViewModel
import com.berkaykbl.shiftmate.presentation.viewmodel.VariableViewModel
import com.berkaykbl.shiftmate.util.nationalHolidays
import java.util.Calendar

@Composable
fun HomeCalendar(
    viewModel: HomeViewModel = hiltViewModel(),
    variableViewModel: VariableViewModel = hiltViewModel(),
) {

    val salaries by variableViewModel.salaries
    val currentCalendar by viewModel.currentCalendar

    val day by viewModel.day
    val month by viewModel.month
    val year by viewModel.year


    Column(
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Mon", Modifier.weight(1f), textAlign = TextAlign.Center, fontSize = 13.sp
            )

            Text(
                text = "Tue", Modifier.weight(1f), textAlign = TextAlign.Center, fontSize = 13.sp
            )
            Text(
                text = "Wed", Modifier.weight(1f), textAlign = TextAlign.Center, fontSize = 13.sp
            )
            Text(
                text = "Thu", Modifier.weight(1f), textAlign = TextAlign.Center, fontSize = 13.sp
            )
            Text(
                text = "Fri", Modifier.weight(1f), textAlign = TextAlign.Center, fontSize = 13.sp
            )
            Text(
                text = "Sat", Modifier.weight(1f), textAlign = TextAlign.Center, fontSize = 13.sp
            )
            Text(
                text = "Sun", Modifier.weight(1f), textAlign = TextAlign.Center, fontSize = 13.sp
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier.fillMaxWidth(),
        ) {

            val newCal = Calendar.getInstance()
            newCal.set(year, month - 1, 1)
            var dayOfWeek = newCal.get(Calendar.DAY_OF_WEEK) - 1
            if (dayOfWeek == 0) dayOfWeek = 7
            var firstDayOfWeek = 0

            val currentCal = viewModel.currentCalendar.value
            val currentDay = listOf(
                currentCal?.get(Calendar.DAY_OF_MONTH) ?: -1,
                currentCal?.get(Calendar.MONTH) ?: -1,
                currentCal?.get(Calendar.YEAR) ?: -1
            )

            items(dayOfWeek - 1) { weekDay ->
                Day(DayModel("", false, false, false, 0)) {}
                firstDayOfWeek++
            }

            items(newCal.getActualMaximum(Calendar.DAY_OF_MONTH)) { dayValue ->
                val isSelected = day == dayValue + 1
                val dayModel = viewModel.findShiftForDay(year, month, dayValue + 1)
                Day(
                    DayModel(
                        (dayValue + 1).toString(),
                        (if (month == currentDay[1] + 1 && year == currentDay[2]) currentDay[0] else -1) == dayValue + 1,
                        isSelected,
                        true,
                        dayModel?.hour ?: 0,
                        dayModel?.shiftType ?: "",
                        nationalHolidays["${dayValue + 1}/${month}"]
                    )
                ) {
                    viewModel.changeDayValues(dayValue + 1, month, year)
                }
                newCal.add(Calendar.DAY_OF_MONTH, 1)
            }
        }
    }
}

