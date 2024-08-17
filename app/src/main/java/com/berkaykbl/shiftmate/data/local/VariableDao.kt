package com.berkaykbl.shiftmate.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.berkaykbl.shiftmate.data.entity.DailyShiftEntity
import com.berkaykbl.shiftmate.data.entity.VariableEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VariableDao {
    @Insert
    suspend fun insert(variableEntity: VariableEntity)

    @Update
    suspend fun update(variableEntity: VariableEntity)

    @Delete
    suspend fun delete(variableEntity: VariableEntity)


    @Query("SELECT * FROM variables WHERE `key` = :key")
    fun getVariablesByKey(key: String): Flow<List<VariableEntity>>

    @Query("SELECT * FROM variables WHERE `key` = :key AND subKey = :subKey ")
    fun getVariableByKeyAndSubKey(key: String, subKey: String): Flow<List<VariableEntity>>


    /*
    @Query("SELECT * FROM shifts WHERE year = :year AND month = :month")
    fun getShiftsForMonth(year: Int, month: Int): Flow<List<DailyShiftEntity>>

    @Query("SELECT * FROM shifts WHERE year = :year")
    suspend fun getShiftsForYear(year: Int): List<DailyShiftEntity>

    @Query("SELECT * FROM shifts")
    suspend fun getAllShifts(): List<DailyShiftEntity>

    @Query("DELETE FROM shifts WHERE year = :year AND month = :month")
    suspend fun deleteShiftByMonth(year: Int, month: Int)

    @Query("DELETE FROM shifts WHERE year = :year AND month = :month AND day = :day")
    suspend fun deleteShiftForDate(day: Int, month: Int, year: Int)

*/


}