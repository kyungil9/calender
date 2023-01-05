package com.calender.main.data.module

import android.content.Context
import androidx.room.Room
import com.calender.main.data.database.DailyDao
import com.calender.main.data.database.Database
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
    fun providesDailyDao(dailyDatabase: Database):DailyDao{
        return dailyDatabase.dailyDao()
    }

    @Provides
    @Singleton
    fun providesDatabaseInstance(@ApplicationContext context: Context):Database{
        return Room.databaseBuilder(context, Database::class.java,"Database").createFromAsset("database/Database.db").build()
    }
}