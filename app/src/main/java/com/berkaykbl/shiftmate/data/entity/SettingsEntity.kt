package com.berkaykbl.shiftmate.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "settings",
)
data class SettingsEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "key")
    val key: String,
    @ColumnInfo(name = "otherKey")
    val otherKey: String = "",
    @ColumnInfo(name = "value")
    val value: String,
)