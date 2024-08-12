package com.berkaykbl.shiftmate.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import com.berkaykbl.shiftmate.R
import java.util.Calendar

@Composable
fun HomeCalendar(day: Int, month: Int, year: Int) {
    var calendarYear by remember {
        mutableIntStateOf(-1)
    }
    var calendarMonth by remember {
        mutableIntStateOf(-1)
    }
    var calendarDay by remember {
        mutableIntStateOf(-1)
    }

    val calendar = Calendar.getInstance().set(calendarYear, calendarMonth, calendarDay)


    LaunchedEffect(Unit) {
        calendarDay = day
        calendarMonth = month
        calendarYear = year
    }

    Column {
        Row {
            Button(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.vector_arrow_prev),
                    contentDescription = null
                )
            }

            Text(text = "$calendarDay/$calendarMonth/$calendarYear")

            Button(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.vector_arrow_next),
                    contentDescription = null
                )
            }
        }

        LazyHorizontalGrid(rows = GridCells.Fixed(7)) {

        }
    }

}