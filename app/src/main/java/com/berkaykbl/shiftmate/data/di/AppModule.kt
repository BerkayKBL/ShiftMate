package com.berkaykbl.shiftmate.data.di

import android.app.Application
import androidx.room.Room
import com.berkaykbl.shiftmate.data.local.Database
import com.berkaykbl.shiftmate.data.repository.DailyShiftRepositoryImpl
import com.berkaykbl.shiftmate.data.repository.VariableRepositoryImpl
import com.berkaykbl.shiftmate.domain.repository.DailyShiftRepository
import com.berkaykbl.shiftmate.domain.repository.VariableRepository
import com.berkaykbl.shiftmate.domain.use_case.dailyShift.DailyShiftsUseCases
import com.berkaykbl.shiftmate.domain.use_case.dailyShift.DeleteDailyShiftForDateUseCase
import com.berkaykbl.shiftmate.domain.use_case.dailyShift.GetAllShiftsUseCase
import com.berkaykbl.shiftmate.domain.use_case.dailyShift.GetShiftForDate
import com.berkaykbl.shiftmate.domain.use_case.dailyShift.GetShiftsForMonthUseCase
import com.berkaykbl.shiftmate.domain.use_case.dailyShift.InsertDailyShiftUseCase
import com.berkaykbl.shiftmate.domain.use_case.dailyShift.UpdateDailyShiftUseCase
import com.berkaykbl.shiftmate.domain.use_case.variable.DeleteVariableByKeyAndSubKeyUseCase
import com.berkaykbl.shiftmate.domain.use_case.variable.GetVariableByKeyAndSubKeyUseCase
import com.berkaykbl.shiftmate.domain.use_case.variable.GetVariablesByKeyUseCase
import com.berkaykbl.shiftmate.domain.use_case.variable.InsertVariableUseCase
import com.berkaykbl.shiftmate.domain.use_case.variable.UpdateVariableUseCase
import com.berkaykbl.shiftmate.domain.use_case.variable.VariableUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): Database {
        return Room.databaseBuilder(
            app,
            Database::class.java,
            "shift_mate_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDailyShiftRepository(db: Database): DailyShiftRepository {
        return DailyShiftRepositoryImpl(db.dailyShiftDao())
    }

    @Provides
    @Singleton
    fun provideVariableRepository(db: Database): VariableRepository {
        return VariableRepositoryImpl(db.variableDao())
    }


    @Provides
    @Singleton
    fun provideDailyShiftsUseCases(repository: DailyShiftRepository): DailyShiftsUseCases {
        return DailyShiftsUseCases(
            getDailyShiftsForMonth = GetShiftsForMonthUseCase(repository),
            insertDailyShifts = InsertDailyShiftUseCase(repository),
            updateDailyShifts = UpdateDailyShiftUseCase(repository),
            getShiftForDay = GetShiftForDate(repository),
            deleteDailyShiftForDateUseCase = DeleteDailyShiftForDateUseCase(repository),
            getAllShiftsUseCase = GetAllShiftsUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideVariableUseCases(repository: VariableRepository) : VariableUseCases {
        return VariableUseCases(
            insertVariableUseCase = InsertVariableUseCase(repository),
            updateVariableUseCase = UpdateVariableUseCase(repository),
            deleteVariableUseCase = DeleteVariableByKeyAndSubKeyUseCase(repository),
            getVariablesByKeyUseCase = GetVariablesByKeyUseCase(repository),
            getVariableByKeyAndSubKeyUseCase = GetVariableByKeyAndSubKeyUseCase(repository),
        )
    }
}