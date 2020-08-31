package com.example.androidfmm.alarm

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time
import java.time.DayOfWeek

// TODO Alarms do not survive a system reboot. Need to repopulate them if "android.intent.action.BOOT_COMPLETED" is triggered

@Entity(tableName = "alarm_table")
data class AlarmItem(
    @PrimaryKey val id: Int,
    @ColumnInfo(name="day_of_week") val dayOfWeek: DayOfWeek,
    @ColumnInfo(name = "alarm_count") val alarmCount: Int,
    @ColumnInfo(name = "alarm_interval") val alarmInterval: Int,
    @ColumnInfo(name = "alarm_time") val alarmTime: Time
)

//data class AlarmItem(val testText: String)