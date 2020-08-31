package com.example.androidfmm.alarm.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidfmm.alarm.AlarmItem

@Database(entities = [AlarmItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun alarmDao(): AlarmDao
}