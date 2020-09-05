package com.example.androidfmm.alarm

import androidx.room.Entity
import androidx.room.PrimaryKey

// TODO Alarms do not survive a system reboot. Need to repopulate them if "android.intent.action.BOOT_COMPLETED" is triggered

@Entity(tableName = "alarm_table")
data class AlarmItem(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val alarmName: String
//    val dayOfWeek: String,
//    val alarmTime: String,
//    val alarmDate: String
//    @ColumnInfo(name = "alarm_count") val alarmCount: Int,
//    @ColumnInfo(name = "alarm_interval") val alarmInterval: Int,
)

//data class AlarmItem(val testText: String)