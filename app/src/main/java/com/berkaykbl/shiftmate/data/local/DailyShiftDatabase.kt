package com.berkaykbl.shiftmate.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.berkaykbl.shiftmate.data.entity.DailyShiftEntity

@Database(
    version = 1,
    entities = [DailyShiftEntity::class],
)
abstract class DailyShiftDatabase : RoomDatabase() {
    abstract fun dailyShiftDao(): DailyShiftDao
}