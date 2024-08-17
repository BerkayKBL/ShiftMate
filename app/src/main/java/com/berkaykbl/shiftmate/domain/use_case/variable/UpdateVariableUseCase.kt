package com.berkaykbl.shiftmate.domain.use_case.variable

import com.berkaykbl.shiftmate.domain.model.VariableModel
import com.berkaykbl.shiftmate.domain.repository.VariableRepository

class UpdateVariableUseCase(private val repository: VariableRepository) {
    suspend operator fun invoke(variableModel: VariableModel) = repository.update(variableModel)

}