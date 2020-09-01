package com.example.androidfmm.alarm.data

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun fromDateToLong(value: Long?): Date? {
        return value?.let {Date(it)}
    }

    @TypeConverter
    fun fromLongToDate(date: Date?): Long? {
        return date?.time?.toLong()
    }
}