package com.calender.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.calender.data.database.converters.Converters
import com.calender.data.database.dao.DailyDao
import com.calender.data.database.dao.ScheduleDao
import com.calender.data.model.local.DailyLocal
import com.calender.data.model.local.ScheduleLocal


@Database(entities = [DailyLocal::class, ScheduleLocal::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class Database :RoomDatabase(){
    abstract fun dailyDao(): DailyDao
    abstract fun scheduleDao(): ScheduleDao
}