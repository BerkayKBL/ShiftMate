package com.berkaykbl.shiftmate.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "wages",
)
data class YearlyWageEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "year")
    val year: Int,
    @ColumnInfo(name = "wage")
    val wage: Int,
)