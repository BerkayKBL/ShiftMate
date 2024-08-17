package com.berkaykbl.shiftmate.domain.repository

import androidx.room.Query
import com.berkaykbl.shiftmate.data.entity.VariableEntity
import com.berkaykbl.shiftmate.domain.model.VariableModel
import kotlinx.coroutines.flow.Flow

interface VariableRepository {

    suspend fun insert(variable: VariableModel)
    suspend fun update(variableModel: VariableModel)
    suspend fun delete(variableModel: VariableModel)
    fun getVariablesByKey(key: String): Flow<List<VariableEntity>>
    fun getVariableByKeyAndSubKey(key: String, subKey: String): Flow<List<VariableEntity>>
}