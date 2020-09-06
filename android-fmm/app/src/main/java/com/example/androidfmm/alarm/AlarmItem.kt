package com.example.androidfmm.alarm

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.OffsetDateTime

// TODO Alarms do not survive a system reboot. Need to repopulate them if "android.intent.action.BOOT_COMPLETED" is triggered

@Entity(tableName = "alarm_table")
data class AlarmItem(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val alarmName: String,
//    val alarmDateTime: OffsetDateTime,
    val alarmCount: Int,
    val alarmInterval: Int
)

//data class AlarmItem(val testText: String)