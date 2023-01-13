package com.calender.presentation.module

import android.content.Context
import androidx.room.Room
import com.calender.data.database.Database
import com.calender.data.database.dao.MemoDao
import com.calender.data.database.dao.RecordDao
import com.calender.data.database.dao.ScheduleDao
import com.calender.data.database.dao.TodoDao
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
    fun providesScheduleDao(database: Database): ScheduleDao {
        return database.scheduleDao()
    }

    @Provides
    @Singleton
    fun providesTodoDao(database: Database): TodoDao {
        return database.toDoDao()
    }

    @Provides
    @Singleton
    fun providesMemoDao(database: Database): MemoDao {
        return database.memoDao()
    }

    @Provides
    @Singleton
    fun providesRecordDao(database: Database): RecordDao {
        return database.recordDao()
    }

    @Provides
    @Singleton
    fun providesDatabaseInstance(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(context, Database::class.java,"Database").createFromAsset("database/Database.db").build()
    }
}