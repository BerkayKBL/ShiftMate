package com.berkaykbl.shiftmate.domain.use_case.variable

import com.berkaykbl.shiftmate.data.entity.VariableEntity
import com.berkaykbl.shiftmate.domain.mapper.toModel
import com.berkaykbl.shiftmate.domain.model.VariableModel
import com.berkaykbl.shiftmate.domain.repository.VariableRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetVariablesByKeyUseCase(private val repository: VariableRepository) {
    operator fun invoke(key: String): Flow<List<VariableModel>> {
        return repository.getVariablesByKey(key).map {
            it.map {
                it.toModel()
            }
        }
    }
}