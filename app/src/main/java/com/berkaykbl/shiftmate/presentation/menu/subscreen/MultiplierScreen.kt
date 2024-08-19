package com.berkaykbl.shiftmate.presentation.menu.subscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.berkaykbl.shiftmate.R
import com.berkaykbl.shiftmate.data.entity.VariableEntity
import com.berkaykbl.shiftmate.domain.model.VariableModel
import com.berkaykbl.shiftmate.presentation.viewmodel.VariableViewModel
import com.berkaykbl.shiftmate.util.maxYear
import com.berkaykbl.shiftmate.util.minYear
import java.time.LocalDate
import java.util.UUID

@Composable
fun MultiplierScreen(
    navController: NavController,
    viewModel: VariableViewModel = hiltViewModel()
) {
    val colorScheme = MaterialTheme.colorScheme
    val multipliers by viewModel.multipliers
    var weekdayMultiplier by remember {
        mutableStateOf("0.0")
    }

    var saturdayMultiplier by remember {
        mutableStateOf("0.0")
    }
    var sundayMultiplier by remember {
        mutableStateOf("0.0")
    }
    var holidayMultiplier by remember {
        mutableStateOf("0.0")
    }

    LaunchedEffect(multipliers) {
        weekdayMultiplier = viewModel.getVariableWithSubKey("multiplier", "weekday")?.value ?: "0.0"
        saturdayMultiplier = viewModel.getVariableWithSubKey("multiplier", "saturday")?.value ?: "0.0"
        sundayMultiplier = viewModel.getVariableWithSubKey("multiplier", "sunday")?.value ?: "0.0"
        holidayMultiplier = viewModel.getVariableWithSubKey("multiplier", "holiday")?.value ?: "0.0"
    }


    Scaffold(
        containerColor = colorScheme.primary,
        topBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 5.dp)
            ) {
                Button(
                    onClick = {
                        navController.popBackStack()
                    },
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.vector_prev),
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                }
                Text(text = "Multipliers", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Button(onClick = {
                    println(weekdayMultiplier)
                    viewModel.setVariable(
                        VariableModel(
                            0,
                            "multiplier",
                            "weekday",
                            weekdayMultiplier
                        )
                    )
                    viewModel.setVariable(
                        VariableModel(
                            0,
                            "multiplier",
                            "saturday",
                            saturdayMultiplier
                        )
                    )
                    viewModel.setVariable(
                        VariableModel(
                            0,
                            "multiplier",
                            "sunday",
                            sundayMultiplier
                        )
                    )
                    viewModel.setVariable(
                        VariableModel(
                            0,
                            "multiplier",
                            "holiday",
                            holidayMultiplier
                        )
                    )
                }) {
                    Text(text = "Save")
                }
            }
        }) { paddingValues ->
        Column(

            modifier = Modifier
                .padding(paddingValues)
                .padding(15.dp, 0.dp)
        ) {

            Column {
                val keyOptions = KeyboardOptions().copy(keyboardType = KeyboardType.Number)
                Spacer(modifier = Modifier.height(15.dp))

                Text(text = "Weekday Multiplier")
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = weekdayMultiplier,
                    onValueChange = { value ->
                        if (!value.contains(Regex("[,\\- ]"))) {
                            val yearValueInt = value.toDoubleOrNull()
                            if (yearValueInt != null) {
                                weekdayMultiplier = value
                            }
                        }
                    },
                    singleLine = true,
                    textStyle = TextStyle.Default.copy(
                        fontSize = 14.sp,
                        lineHeight = 11.sp
                    ),
                    colors = TextFieldDefaults.colors().copy(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        focusedLabelColor = colorScheme.tertiary,
                    ),
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp)),
                    keyboardOptions = keyOptions
                )

                Spacer(modifier = Modifier.height(15.dp))

                Text(text = "Saturday Multiplier")
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = saturdayMultiplier,
                    onValueChange = { value ->
                        if (!value.contains(Regex("[,\\- ]"))) {
                            val yearValueInt = value.toDoubleOrNull()
                            if (yearValueInt != null) {
                                saturdayMultiplier = value
                            }
                        }
                    },
                    singleLine = true,
                    textStyle = TextStyle.Default.copy(
                        fontSize = 14.sp,
                        lineHeight = 11.sp
                    ),
                    colors = TextFieldDefaults.colors().copy(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        focusedLabelColor = colorScheme.tertiary,
                    ),
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp)),
                    keyboardOptions = keyOptions
                )

                Spacer(modifier = Modifier.height(15.dp))

                Text(text = "Sunday Multiplier")
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = sundayMultiplier,
                    onValueChange = { value ->
                        if (!value.contains(Regex("[,\\- ]"))) {
                            val yearValueInt = value.toDoubleOrNull()
                            if (yearValueInt != null) {
                                sundayMultiplier = value
                            }
                        }
                    },
                    singleLine = true,
                    textStyle = TextStyle.Default.copy(
                        fontSize = 14.sp,
                        lineHeight = 11.sp
                    ),
                    colors = TextFieldDefaults.colors().copy(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        focusedLabelColor = colorScheme.tertiary,
                    ),
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp)),
                    keyboardOptions = keyOptions
                )

                Spacer(modifier = Modifier.height(15.dp))

                Text(text = "Holiday Multiplier")
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = holidayMultiplier,
                    onValueChange = { value ->
                        if (!value.contains(Regex("[,\\- ]"))) {
                            val yearValueInt = value.toDoubleOrNull()
                            if (yearValueInt != null) {
                                holidayMultiplier = value
                            }
                        }
                    },
                    singleLine = true,
                    textStyle = TextStyle.Default.copy(
                        fontSize = 14.sp,
                        lineHeight = 11.sp
                    ),
                    colors = TextFieldDefaults.colors().copy(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        focusedLabelColor = colorScheme.tertiary,
                    ),
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp)),
                    keyboardOptions = keyOptions
                )
            }
        }
    }
}