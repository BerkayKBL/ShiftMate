package com.berkaykbl.shiftmate.domain.mapper

import com.berkaykbl.shiftmate.data.entity.VariableEntity
import com.berkaykbl.shiftmate.domain.model.VariableModel

fun VariableEntity.toModel() : VariableModel {
    return VariableModel(
        id = id,
        key = key,
        subKey = subKey,
        value = value
    )
}

fun VariableModel.toEntity() : VariableEntity {
    return VariableEntity(
        id = id,
        key = key,
        subKey = subKey,
        value = value
    )
}