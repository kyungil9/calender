package com.calender.main.data.module

import android.content.Context
import androidx.room.Room
import com.calender.data.database.dao.DailyDao
import com.calender.data.database.Database
import com.calender.data.database.dao.ScheduleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun providesDailyDao(database: Database): DailyDao {
        return database.dailyDao()
    }

    @Provides
    @Singleton
    fun providesScheduleDao(database: Database): ScheduleDao {
        return database.scheduleDao()
    }

    @Provides
    @Singleton
    fun providesDatabaseInstance(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(context, Database::class.java,"Database").createFromAsset("database/Database.db").build()
    }
}