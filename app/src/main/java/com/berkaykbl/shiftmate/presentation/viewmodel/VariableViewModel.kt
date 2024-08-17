package com.berkaykbl.shiftmate.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.berkaykbl.shiftmate.domain.mapper.toModel
import com.berkaykbl.shiftmate.domain.model.VariableModel
import com.berkaykbl.shiftmate.domain.use_case.variable.VariableUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VariableViewModel @Inject constructor(
    private val useCases: VariableUseCases
) : ViewModel() {
    private val _salaries = mutableStateOf(emptyList<VariableModel>())
    private val _multipliers = mutableStateOf(emptyList<VariableModel>())
    private val _bonuses = mutableStateOf(emptyList<VariableModel>())

    val salaries: State<List<VariableModel>> get() = _salaries
    val multipliers: State<List<VariableModel>> get() = _multipliers
    val bonuses: State<List<VariableModel>> get() = _bonuses

    init {
        getVariables("salary")
        getVariables("multiplier")
    }

    fun getVariables(key: String) {
        viewModelScope.launch {
            useCases.getVariablesByKeyUseCase.invoke(key).collect {
                if (key == "salary") {
                    _salaries.value = it.sortedBy { e -> e.subKey }
                } else if (key == "multiplier") {
                    println("qweqwe")
                    println(it)
                    _multipliers.value = it
                } else if (key == "bonus") {
                    _multipliers.value = it
                }
            }
        }
    }

    fun getVariableWithSubKey(key: String, subKey: String): VariableModel? {
        var model: VariableModel? = null
        if (key == "salary") {
            model = salaries.value.find { it.subKey == subKey }
        } else if (key == "multiplier") {
            model = multipliers.value.find { it.subKey == subKey }
        } else if (key == "bonus") {
            model = bonuses.value.find { it.subKey == subKey }
        }
        return model
    }

    fun setVariable(variableModel: VariableModel) {
        viewModelScope.launch {
            val variable = getVariableWithSubKey(variableModel.key, variableModel.subKey)
            println(variable)
            println(variableModel)
            if (variable == null) {
                println("variable")
                useCases.insertVariableUseCase.invoke(variableModel)
            } else {
                println("vaxxxle")
                useCases.updateVariableUseCase.invoke(variableModel.copy(id = variable.id))
            }
            getVariables(variableModel.key)
        }
    }

    fun deleteVariable(variableModel: VariableModel) {
        viewModelScope.launch {
            useCases.deleteVariableUseCase.invoke(variableModel)
        }
    }
}