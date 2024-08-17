package com.berkaykbl.shiftmate.domain.use_case.variable

data class VariableUseCases(
    val insertVariableUseCase: InsertVariableUseCase,
    val updateVariableUseCase: UpdateVariableUseCase,
    val getVariablesByKeyUseCase: GetVariablesByKeyUseCase,
    val getVariableByKeyAndSubKeyUseCase: GetVariableByKeyAndSubKeyUseCase,
    val deleteVariableUseCase: DeleteVariableByKeyAndSubKeyUseCase,

)