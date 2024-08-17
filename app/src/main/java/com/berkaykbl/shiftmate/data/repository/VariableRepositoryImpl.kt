package com.berkaykbl.shiftmate.data.repository

import com.berkaykbl.shiftmate.data.entity.VariableEntity
import com.berkaykbl.shiftmate.data.local.DailyShiftDao
import com.berkaykbl.shiftmate.data.local.VariableDao
import com.berkaykbl.shiftmate.domain.mapper.toEntity
import com.berkaykbl.shiftmate.domain.model.VariableModel
import com.berkaykbl.shiftmate.domain.repository.VariableRepository
import kotlinx.coroutines.flow.Flow

class VariableRepositoryImpl(private val variableDao: VariableDao) : VariableRepository {
    override suspend fun insert(variable: VariableModel) {
        variableDao.insert(variable.toEntity())
    }

    override suspend fun update(variableModel: VariableModel) {
        variableDao.update(variableModel.toEntity())
    }
    override suspend fun delete(variableModel: VariableModel) {
        variableDao.delete(variableModel.toEntity())
    }

    override fun getVariablesByKey(key: String): Flow<List<VariableEntity>> {
        return variableDao.getVariablesByKey(key)
    }

    override fun getVariableByKeyAndSubKey(key: String, subKey: String): Flow<List<VariableEntity>> {
        return variableDao.getVariableByKeyAndSubKey(key, subKey)
    }
}