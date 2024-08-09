package com.berkaykbl.shiftmate.data.di

import android.app.Application
import androidx.room.Room
import com.berkaykbl.shiftmate.data.local.DailyShiftDatabase
import com.berkaykbl.shiftmate.data.repository.DailyShiftRepositoryImpl
import com.berkaykbl.shiftmate.domain.repository.DailyShiftRepository
import com.berkaykbl.shiftmate.domain.use_case.DailyShiftsUseCases
import com.berkaykbl.shiftmate.domain.use_case.GetDailyShiftsByMonthUseCase
import com.berkaykbl.shiftmate.domain.use_case.InsertDailyShiftsUseCase
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
    fun provideDailyShiftDatabase(app: Application): DailyShiftDatabase {
        return Room.databaseBuilder(
            app,
            DailyShiftDatabase::class.java,
            "shift_mate_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDailyShiftRepository(db: DailyShiftDatabase): DailyShiftRepository {
        return DailyShiftRepositoryImpl(db.dailyShiftDao())
    }


    @Provides
    @Singleton
    fun provideDailyShiftsUseCases(repository: DailyShiftRepository): DailyShiftsUseCases {
        return DailyShiftsUseCases(
            getDailyShiftsByMonth = GetDailyShiftsByMonthUseCase(repository),
            insertDailyShifts = InsertDailyShiftsUseCase(repository)
        )
    }
}