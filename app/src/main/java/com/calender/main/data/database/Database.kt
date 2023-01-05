package com.calender.main.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.calender.main.data.entity.Daily

@Database(entities = [Daily::class], version = 1, exportSchema = true)
abstract class Database :RoomDatabase(){
    abstract fun dailyDao(): DailyDao
}