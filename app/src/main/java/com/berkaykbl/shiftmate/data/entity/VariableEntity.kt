package com.berkaykbl.shiftmate.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "variables",
)
data class VariableEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "key")
    val key: String,
    @ColumnInfo(name = "subKey")
    val subKey: String,
    @ColumnInfo(name = "value")
    val value: String,
)