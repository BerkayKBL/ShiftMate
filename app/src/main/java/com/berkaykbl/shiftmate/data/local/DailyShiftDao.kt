package com.berkaykbl.shiftmate.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.berkaykbl.shiftmate.data.entity.DailyShiftEntity

@Dao
interface DailyShiftDao {
    @Insert
    suspend fun insert(dailyShift: DailyShiftEntity)

    @Query("SELECT * FROM shifts WHERE year = :year AND month = :month AND day = :day")
    suspend fun getShiftsForDate(year: Int, month: Int, day: Int): List<DailyShiftEntity>

    @Query("SELECT * FROM shifts WHERE year = :year AND month = :month")
    suspend fun getShiftsForMonth(year: Int, month: Int): List<DailyShiftEntity>

    @Query("SELECT * FROM shifts WHERE year = :year")
    suspend fun getShiftsForYear(year: Int): List<DailyShiftEntity>

    @Query("SELECT * FROM shifts")
    suspend fun getAllShifts(): List<DailyShiftEntity>

    @Query("DELETE FROM shifts WHERE year = :year AND month = :month")
    suspend fun deleteShiftByMonth(year: Int, month: Int)

    @Query("DELETE FROM shifts WHERE year = :year AND month = :month AND day = :day")
    suspend fun deleteShiftByDate(year: Int, month: Int, day: Int)


    @Update
    suspend fun update(dailyShift: DailyShiftEntity)



}