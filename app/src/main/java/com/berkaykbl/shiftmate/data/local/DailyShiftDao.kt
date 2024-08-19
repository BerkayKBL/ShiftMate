package com.berkaykbl.shiftmate.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.berkaykbl.shiftmate.data.entity.DailyShiftEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyShiftDao {
    @Insert
    suspend fun insert(dailyShift: DailyShiftEntity)

    @Query("SELECT * FROM shifts WHERE year = :year AND month = :month AND day = :day")
    fun getShiftForDate(year: Int, month: Int, day: Int): Flow<List<DailyShiftEntity>>

    @Query("SELECT * FROM shifts WHERE year = :year AND month = :month")
    fun getShiftsForMonth(year: Int, month: Int): Flow<List<DailyShiftEntity>>

    @Query("SELECT * FROM shifts WHERE year = :year")
    suspend fun getShiftsForYear(year: Int): List<DailyShiftEntity>

    @Query("SELECT * FROM shifts")
    fun getAllShifts(): Flow<List<DailyShiftEntity>>

    @Query("DELETE FROM shifts WHERE year = :year AND month = :month")
    suspend fun deleteShiftByMonth(year: Int, month: Int)

    @Query("DELETE FROM shifts WHERE year = :year AND month = :month AND day = :day")
    suspend fun deleteShiftForDate(day: Int, month: Int, year: Int)

    @Update
    suspend fun update(dailyShift: DailyShiftEntity)



}