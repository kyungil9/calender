package com.calender.data.database.converters

import android.util.Log
import androidx.room.TypeConverter
import com.google.gson.Gson
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class Converters {
    @TypeConverter
    fun localDateToJson(value: LocalDate) : String?{
        return Gson().toJson(value.toString())
    }
    @TypeConverter
    fun jsonToLocalDate(value : String) : LocalDate?{
        val string = Gson().fromJson(value,String::class.java)
        val format = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return LocalDate.parse(string, format)
    }

    @TypeConverter
    fun localTimeToJson(value: LocalTime) : String?{
        return Gson().toJson(value.toString())
    }

    @TypeConverter
    fun jsonToLocalTime(value: String) : LocalTime?{
        return Gson().fromJson(value,LocalTime::class.java)
    }

    @TypeConverter
    fun localDateTimeToJson(value: LocalDateTime?) : Long?{
        return value?.atZone(ZoneOffset.UTC)?.toInstant()?.toEpochMilli()
    }

    @TypeConverter
    fun jsonToLocalDateTime(value: Long?) : LocalDateTime? {
        return value?.let { LocalDateTime.ofInstant(Instant.ofEpochMilli(value),ZoneOffset.UTC)}
    }
}