package com.calender.main.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.calender.main.data.entity.Daily
import com.calender.main.ui.base.Converters

@Database(entities = [Daily::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class Database :RoomDatabase(){
    abstract fun dailyDao(): DailyDao
}