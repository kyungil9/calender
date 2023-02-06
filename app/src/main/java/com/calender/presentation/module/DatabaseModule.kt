package com.calender.presentation.module

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.calender.data.database.Database
import com.calender.data.database.dao.*
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
    fun providesTagDao(database: Database) : TagDao{
        return database.tagDao()
    }

    @Singleton
    val MIGRATION_6_7 = object : Migration(6,7){
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("insert into tag values (null,'basic',0)")
            database.execSQL("insert into tag values (null,'공부',1)")
            database.execSQL("insert into tag values (null,'운동',1)")
            database.execSQL("insert into tag values (null,'휴식',1)")
            database.execSQL("insert into tag values (null,'이동시간',1)")
            database.execSQL("insert into tag values (null,'개인일정',1)")
            database.execSQL("insert into tag values (null,'수면',1)")
        }
    }

    @Provides
    @Singleton
    fun providesDatabaseInstance(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(context, Database::class.java,"Database.db").addMigrations(MIGRATION_6_7).build()
    }
}