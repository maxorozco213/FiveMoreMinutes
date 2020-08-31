package com.example.androidfmm.alarm

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time
import java.time.DayOfWeek

@Entity(tableName = "alarm_table")
//data class AlarmItem(
//    val dayOfWeek: DayOfWeek,
//    val alarmCount: Int,
//    val alarmInterval: Int,
//    val alarmTime: Time
//)

data class AlarmItem(val testText: String)