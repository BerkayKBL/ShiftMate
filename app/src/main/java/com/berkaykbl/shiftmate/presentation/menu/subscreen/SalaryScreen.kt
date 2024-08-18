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
fun SalaryScreen(
    navController: NavController,
    viewModel: VariableViewModel = hiltViewModel()
) {
    val colorScheme = MaterialTheme.colorScheme
    val salaries by viewModel.salaries

    val oldSalaries = mutableListOf<VariableModel>()
    val list = mutableListOf<VariableModel>()
    oldSalaries.addAll(salaries)

    var newSalaries = remember {
        list.toMutableStateList()
    }

    LaunchedEffect(salaries) {
        newSalaries.clear()
        newSalaries.addAll(salaries)
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
                Button(onClick = {
                    val deleteElements = oldSalaries.filter { it.subKey !in newSalaries.map { it.subKey }}
                    val changedElements = mutableListOf<VariableModel>()
                    newSalaries.forEach { element ->
                        val findElement = salaries.find { it.subKey == element.subKey }
                        if (findElement == null) {
                            changedElements.add(element)
                        } else if (findElement.value != element.value) {
                            changedElements.add(element)
                        }
                    }
                    changedElements.forEach { element ->
                        viewModel.setVariable(element)
                    }
                    deleteElements.forEach { element ->
                        println(element)
                        viewModel.deleteVariable(element)
                    }

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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {
                        newSalaries.add(VariableModel(0, "salary", "0", "0"))
                    }
                    .padding(15.dp, 5.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.vector_add),
                    contentDescription = null,
                    Modifier.size(35.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    "Add Year",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            LazyColumn(
            ) {
                val keyOptions = KeyboardOptions().copy(keyboardType = KeyboardType.Number)

                items(newSalaries.size, key = { k->
                    UUID.randomUUID()
                }) { i ->
                    val salary = newSalaries[i]
                    var salaryYear = remember {
                        mutableStateOf(salary.subKey)
                    }
                    var salaryValue = remember {
                        mutableStateOf(salary.value)
                    }
                    var editable = remember {
                        mutableStateOf(false)
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        TextField(
                            value = salaryYear.value,
                            onValueChange = { yearValue ->
                                if (!yearValue.contains(Regex("[.,\\- ]"))) {
                                    val yearValueInt = yearValue.toIntOrNull()
                                    if ((yearValueInt ?: 0) <= maxYear) {
                                        salaryYear.value = yearValue
                                    }
                                }
                            },
                            label = { Text("Year") },
                            singleLine = true,
                            readOnly = !editable.value,
                            textStyle = TextStyle.Default.copy(
                                fontSize = 14.sp,
                                lineHeight = 11.sp
                            ),
                            colors = TextFieldDefaults.colors().copy(
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                focusedLabelColor = colorScheme.tertiary
                            ),
                            modifier = Modifier
                                .width(70.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .padding(0.dp),
                            keyboardOptions = keyOptions
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        TextField(
                            value = salaryValue.value,
                            onValueChange = { value ->
                                if (!value.contains(Regex("[.,\\- ]"))) {
                                    salaryValue.value = value
                                }
                            },
                            label = { Text("Salary") },
                            singleLine = true,
                            readOnly = !editable.value,
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
                                .width(90.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .padding(0.dp),
                            keyboardOptions = keyOptions
                        )

                        Spacer(modifier = Modifier.weight(1f))


                        Icon(
                            painter = painterResource(id = if (editable.value) R.drawable.vector_save else R.drawable.vector_edit),
                            contentDescription = null,
                            modifier = Modifier
                                .clip(RoundedCornerShape(100))
                                .clickable {
                                    if (editable.value) {
                                        newSalaries[i] =
                                            salary.copy(
                                                subKey = salaryYear.value,
                                                value = salaryValue.value
                                            )
                                    }
                                    editable.value = !editable.value
                                }
                                .padding(10.dp),
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.vector_delete),
                            contentDescription = null,
                            modifier = Modifier
                                .clip(RoundedCornerShape(100))
                                .clickable {
                                    newSalaries.removeAt(i)
                                }
                                .padding(10.dp),
                        )
                    }
                }
            }
        }
    }
}