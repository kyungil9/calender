package com.calender.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import com.calender.data.database.converters.Converters
import com.calender.data.database.dao.*
import com.calender.data.model.local.*


@Database(entities = [ScheduleLocal::class,ToDoCheckLocal::class,MemoLocal::class,RecordLocal::class,TagLocal::class], version = 7, exportSchema = true)
@TypeConverters(Converters::class)
abstract class Database :RoomDatabase(){
    abstract fun scheduleDao(): ScheduleDao
    abstract fun toDoDao(): TodoDao
    abstract fun memoDao(): MemoDao
    abstract fun recordDao(): RecordDao
    abstract fun tagDao() : TagDao

}