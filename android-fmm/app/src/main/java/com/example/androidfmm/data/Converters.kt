package com.example.androidfmm.data

import androidx.room.TypeConverter
import com.example.androidfmm.alarm.AlarmItem
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
// Define dependency in app:gradle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converters {
    private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    @TypeConverter
    @JvmStatic
    fun fromDateToString(value: String?): OffsetDateTime? {
        return value?.let {
            return formatter.parse(value, OffsetDateTime::from)
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromStringToDate(dateTime: OffsetDateTime?): String? {
        return dateTime?.format(formatter)
    }

    @TypeConverter
    @JvmStatic
    fun fromArrayListToString(alarmList: List<AlarmItem?>): String {
        return Gson().toJson(alarmList)
    }

    @TypeConverter
    @JvmStatic
    fun fromStringToArrayList(alarmList: String?): List<AlarmItem> {
        val listType = object:TypeToken<ArrayList<AlarmItem>>(){}.type
        return Gson().fromJson(alarmList, listType)
    }
}