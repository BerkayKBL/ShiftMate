package com.berkaykbl.shiftmate.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.berkaykbl.shiftmate.R
import com.berkaykbl.shiftmate.domain.model.DailyShift
import com.berkaykbl.shiftmate.presentation.component.EntryDialog
import com.berkaykbl.shiftmate.presentation.home.component.Detail
import com.berkaykbl.shiftmate.presentation.model.VariableModel
import com.berkaykbl.shiftmate.presentation.viewmodel.HomeViewModel
import com.berkaykbl.shiftmate.presentation.viewmodel.VariableViewModel
import com.berkaykbl.shiftmate.util.Screens
import com.berkaykbl.shiftmate.util.leaveTypes
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val day by viewModel.day
    val month by viewModel.month
    val year by viewModel.year
    val colorScheme = MaterialTheme.colorScheme




    var showButtonDialog by remember {
        mutableStateOf(false)
    }
    var showEntryDialog by remember {
        mutableStateOf(false)
    }

    var entryTitle by remember {
        mutableStateOf("")
    }
    var entryValue by remember {
        mutableStateOf("")
    }
    var entryType by remember {
        mutableStateOf("")
    }

    var rotate = if(showButtonDialog) 45f else 0f
    LaunchedEffect(Unit) {
        viewModel.setCurrentCalendar(Calendar.getInstance())
        val dayVal = viewModel.currentCalendar.value!!.get(Calendar.DAY_OF_MONTH)
        val monthVal = viewModel.currentCalendar.value!!.get(Calendar.MONTH) + 1
        val yearVal = viewModel.currentCalendar.value!!.get(Calendar.YEAR)
        viewModel.changeDayValues(-1, monthVal, yearVal)
    }


    if (showButtonDialog) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f))
                .zIndex(1f)
                .clickable {
                    showButtonDialog = false
                },
            contentAlignment = Alignment.BottomEnd,
        ) {
            Column(
                modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.End
            ) {
                // BONUS
                Button(
                    onClick = {
                        entryTitle = "Bonus"
                        entryValue = "0"
                        entryType = "bonus"
                        showEntryDialog = true
                    }, colors = ButtonDefaults.buttonColors().copy(
                        containerColor = colorScheme.primary, contentColor = colorScheme.secondary
                    ), contentPadding = PaddingValues(10.dp, 5.dp)
                ) {
                    Text(
                        text = "Bonus", fontSize = 18.sp
                    )
                }
                // LEAVE
                Button(
                    onClick = {
                        entryTitle = "Leave"
                        entryValue = "0"
                        entryType = "leave"
                        showEntryDialog = true
                    }, colors = ButtonDefaults.buttonColors().copy(
                        containerColor = colorScheme.primary, contentColor = colorScheme.secondary
                    ), contentPadding = PaddingValues(10.dp, 5.dp)
                ) {
                    Text(
                        text = "Leave", fontSize = 18.sp
                    )
                }

                // OVERTIME
                Button(
                    onClick = {
                        entryTitle = "Overtime"
                        entryValue = "0"
                        entryType = "overtime"
                        showEntryDialog = true
                    }, colors = ButtonDefaults.buttonColors().copy(
                        containerColor = colorScheme.primary, contentColor = colorScheme.secondary
                    ), contentPadding = PaddingValues(10.dp, 5.dp)
                ) {
                    Text(
                        text = "Overtime", fontSize = 18.sp
                    )
                }
                Button(
                    onClick = {
                        showButtonDialog = false
                    },
                    colors = ButtonDefaults.buttonColors().copy(
                        containerColor = colorScheme.primary, contentColor = colorScheme.secondary
                    ),
                    shape = RoundedCornerShape(100),
                    modifier = Modifier
                        .size(70.dp)
                        .rotate(rotate),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.vector_add),
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)
                    )
                }
            }

        }
    }
    EntryDialog(
        enabled = showEntryDialog,
        title = entryTitle,
        textComponents = {
            if (entryType == "overtime" || entryType == "bonus") {
                val keyOptions = KeyboardOptions().copy(keyboardType = KeyboardType.Number)
                TextField(
                    value = entryValue,
                    onValueChange = {
                        if (!it.contains(Regex("[.,\\- ]"))) {
                            entryValue = it
                        }
                    },
                    singleLine = true,
                    keyboardOptions = keyOptions,
                )
            } else {
                Column(
                    modifier = Modifier
                        .wrapContentWidth()
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    val leaveIds = leaveTypes
                    var nowId = 0
                    Column(
                        modifier = Modifier.width(170.dp)
                    ) {
                        while (nowId < leaveIds.size) {
                            val leaveDetail = leaveIds.entries.toList()[nowId]
                            Row(
                                modifier = Modifier
                                    .clickable {
                                        entryValue = leaveDetail.key
                                    }
                                    .fillMaxWidth()
                                    .padding(10.dp, 5.dp),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = (entryValue == leaveDetail.key), onClick = {},
                                    modifier = Modifier.size(30.dp),
                                    colors = RadioButtonDefaults.colors()
                                        .copy(selectedColor = colorScheme.secondary)
                                )
                                Text(
                                    text = stringResource(id = leaveDetail.value),
                                    fontSize = 16.sp
                                )
                            }
                            nowId++
                        }

                    }
                }
            }
        },
        confirmCallback = {
            showEntryDialog = false

            if (entryType == "overtime") {
                viewModel.setDailyShift(
                    DailyShift(
                        0,
                        day,
                        month,
                        year,
                        entryType,
                        entryValue.toInt()
                    )
                )
            } else if (entryType == "leave") {
                viewModel.setDailyShift(
                    DailyShift(
                        0,
                        day,
                        month,
                        year,
                        entryValue,
                        0
                    )
                )
            } else if (entryType == "bonus") {
                viewModel.setDailyShift(
                    DailyShift(
                        0,
                        -1,
                        month,
                        year,
                        entryType,
                        entryValue.toInt()
                    )
                )
            }
            /*viewModel.setDailyShift(DailyShift(
                0,
                day,
                month,
                year,
                "overtime",
                entryValue.toInt()
            ))*/
        }
    ) {
        showEntryDialog = false
    }
    Scaffold(
        floatingActionButton = {
            Button(
                onClick = {
                    if (day != -1) {
                        showButtonDialog = true
                    }
                },
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = colorScheme.primary, contentColor = colorScheme.secondary
                ),
                shape = RoundedCornerShape(100),
                modifier = Modifier.size(70.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.vector_add),
                    contentDescription = null,
                    modifier = Modifier.size(35.dp)
                )
            }
        },
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val buttonColors = ButtonDefaults.buttonColors()
                    .copy(contentColor = colorScheme.primary, containerColor = Color.Transparent)
                Button(
                    onClick = {
                        navController.navigate(Screens.Menu.route) {
                            popUpTo(0)
                        }
                    },
                    colors = buttonColors,
                    modifier = Modifier.padding(0.dp),
                    contentPadding = PaddingValues(10.dp, 0.dp),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.vector_prev),
                        contentDescription = null,
                        Modifier
                            .size(40.dp)
                            .padding(0.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 0.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            viewModel.changeDayValues(-1, month - 1, year)
                        },
                        colors = buttonColors,
                        modifier = Modifier.padding(0.dp),
                        contentPadding = PaddingValues(10.dp, 0.dp),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.vector_arrow_prev),
                            contentDescription = null,
                            Modifier
                                .size(40.dp)
                                .padding(0.dp)
                        )
                    }

                    Text(
                        text = "${month}/$year", fontSize = 18.sp, fontWeight = FontWeight.Bold
                    )

                    Button(
                        onClick = {
                            viewModel.changeDayValues(-1, month + 1, year)

                        },
                        colors = buttonColors,
                        modifier = Modifier.padding(0.dp),
                        contentPadding = PaddingValues(10.dp, 0.dp),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.vector_arrow_next),
                            contentDescription = null,
                            Modifier
                                .size(40.dp)
                                .padding(0.dp)
                        )
                    }
                }

            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(5.dp, 0.dp)
        ) {
            HomeCalendar()
            Row(
                Modifier.fillMaxWidth()
            ) {
                val dayDetails = viewModel.dayDetails.value
                Column(
                    Modifier
                        .weight(1f)
                        .padding(5.dp, 0.dp),
                ) {
                    Detail(title = "Salary Details", subtitle = "Total: ${dayDetails.totalSalary}₺")
                    Detail(title = "Base Salary", subtitle = "${dayDetails.baseSalary}₺")
                    Detail(title = "Overtime Pay", subtitle = "${dayDetails.overtimePay}₺")
                    Detail(title = "Bonuses", subtitle = "${dayDetails.bonuses}₺")
                    Detail(title = "Leave Deductions", subtitle = "${dayDetails.leaveDeductions}₺")
                }
                Column(
                    Modifier
                        .weight(1f)
                        .padding(5.dp, 0.dp)
                ) {
                    Detail(
                        title = "Overtime Details", subtitle = "Total: ${dayDetails.totalOvertime}"
                    )
                    Detail(title = "Weekday Overtime", subtitle = "${dayDetails.weekdayOvertime}")
                    Detail(title = "Saturday Overtime", subtitle = "${dayDetails.saturdayOvertime}")
                    Detail(title = "Sunday Overtime", subtitle = "${dayDetails.sundayOvertime}")
                    Detail(title = "Holiday Overtime", subtitle = "${dayDetails.holidayOvertime}")
                }
                Column(
                    Modifier
                        .weight(1f)
                        .padding(5.dp, 0.dp)
                ) {
                    Detail(
                        title = "Leave Details",
                        subtitle = "Total: ${dayDetails.totalLeave}\nDeducted Day: ${dayDetails.deductedDay}"
                    )
                    Detail(title = "Paid Leave", subtitle = "${dayDetails.paidLeave}")
                    Detail(title = "Unpaid Leave", subtitle = "${dayDetails.unpaidLeave}")
                    Detail(title = "Sick Leave", subtitle = "${dayDetails.sickLeave}")
                    Detail(title = "Annual Leave", subtitle = "${dayDetails.annualLeave}")
                }
            }

        }
    }
}