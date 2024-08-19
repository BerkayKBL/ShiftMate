package com.berkaykbl.shiftmate.presentation.menu.subscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import com.berkaykbl.shiftmate.presentation.viewmodel.HomeViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.berkaykbl.shiftmate.R
import com.berkaykbl.shiftmate.domain.model.DailyShift
import com.berkaykbl.shiftmate.presentation.model.MonthlyShiftDetailModel
import com.berkaykbl.shiftmate.presentation.model.VariableModel
import com.berkaykbl.shiftmate.presentation.ui.theme.cardTransparency
import com.berkaykbl.shiftmate.presentation.viewmodel.VariableViewModel
import com.berkaykbl.shiftmate.util.Screens
import java.util.UUID
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MonthlyDataScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
    variableViewModel: VariableViewModel = hiltViewModel()
) {
    val colorScheme = MaterialTheme.colorScheme

    val salaries by variableViewModel.salaries
    val sortedSalaries = salaries.sortedBy { it.subKey }
    val days by homeViewModel.allDays

    val years = remember {
        mutableStateMapOf<String, String>()
    }

    LaunchedEffect(salaries) {
        sortedSalaries.forEach {
            years[it.subKey] = it.value
        }
    }


    Scaffold(
        containerColor = colorScheme.primary,
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 5.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            navController.navigate(Screens.Menu.route)
                        },
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.vector_prev),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
                Text(
                    text = "Salaries", fontSize = 18.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .wrapContentWidth()
                )
            }
        }) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            LazyColumn(
                Modifier.padding(10.dp, 0.dp)
            ) {
                val variableModel by homeViewModel.variableModel
                items(years.size,
                    key = {
                        UUID.randomUUID()
                    }) { i ->
                    val year = years.keys.toList()[i]
                    val salary = years[year]
                    val monthlyShifts = remember {
                        mutableStateOf<List<MonthlyShiftDetailModel>>(emptyList())
                    }
                    val monthShifts = ArrayList<MonthlyShiftDetailModel>()
                    var yearSalary = remember {
                        mutableStateOf(0.0)
                    }
                    var month = 1
                    while (month <= 12) {
                        val monthlyShiftsValue =
                            days.filter { it.year == year.toInt() && it.month == month }
                        if (monthlyShiftsValue.isEmpty()) {
                            yearSalary.value += salary!!.toInt()
                        } else {
                            val monthlyDetail = homeViewModel.calculateMonthlyDetail(
                                monthlyShiftsValue,
                                variableModel
                            )
                            monthShifts.add(monthlyDetail)
                            yearSalary.value += monthlyDetail.totalSalary
                        }
                        month++
                    }
                    monthlyShifts.value = monthShifts

                    Card(
                        colors = CardDefaults.cardColors().copy(
                            containerColor = cardTransparency,
                            contentColor = colorScheme.secondary
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(10.dp, 0.dp)
                                .fillMaxWidth()
                        ) {
                            Text(text = year)
                            Spacer(modifier = Modifier.weight(1f))
                            Text(text = yearSalary.value.toString())
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    monthlyShifts.value.forEach {
                        Card(
                            colors = CardDefaults.cardColors().copy(
                                containerColor = cardTransparency,
                                contentColor = colorScheme.secondary
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp, 5.dp),
                                horizontalAlignment = Alignment.CenterHorizontally

                            ) {
                                Text(
                                    text = "${it.month}/${it.year}",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = "Total Salary",
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = it.totalSalary.toString(),
                                            fontSize = 15.sp
                                        )
                                    }
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = "Total Overtime",
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = it.totalOvertime.toString() + " hour(s)",
                                            fontSize = 15.sp
                                        )
                                    }
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = "Total Leave",
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = it.totalLeave.toString() + " day(s)",
                                            fontSize = 15.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                /*items(years.size) { i ->
                   val year = years.keys.toList()[i]
                    val salary = years[year]
                    var yearSalary = remember {
                        mutableStateOf(0.0)
                    }
                    println(yearSalary)
                    val yearlyShifts = homeViewModel.getShiftsForYear(year.toInt())
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .padding(10.dp, 0.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = year)
                        Text(text = yearSalary.toString())
                    }

                    var y = 0.0
                    var month = 1
                    while (month <= 12) {
                        println("qwe")
                        val monthlyShifts = yearlyShifts.filter { it.month == month }
                        if (monthlyShifts.isEmpty()) y += salary!!.toInt()
                        else {
                            val monthlyDetail = homeViewModel.calculateMonthlyDetail(monthlyShifts)
                            //yearlySalary += monthlyDetail.totalSalary
                        }
                        month++
                    }
                    println(y)
                    yearSalary.value = y
                }*/
            }
        }
    }
}
