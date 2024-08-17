package com.berkaykbl.shiftmate.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.berkaykbl.shiftmate.data.entity.DailyShiftEntity
import com.berkaykbl.shiftmate.data.entity.VariableEntity

@Database(
    version = 1,
    entities = [DailyShiftEntity::class, VariableEntity::class],
)
abstract class Database : RoomDatabase() {
    abstract fun dailyShiftDao(): DailyShiftDao
    abstract fun variableDao() : VariableDao
}