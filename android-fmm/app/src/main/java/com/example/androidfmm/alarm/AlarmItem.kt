package com.example.androidfmm.alarm

import android.os.Parcelable
import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.time.OffsetDateTime

// TODO Alarms do not survive a system reboot. Need to repopulate them if "android.intent.action.BOOT_COMPLETED" is triggered

@Parcelize
@Entity(tableName = "alarm_table")
data class AlarmItem(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val alarmName: String,
    val alarmDateTime: OffsetDateTime,
    val alarmCount: Int,
    val alarmInterval: Int,
    var isActive: Boolean,
    val alarmGroup: List<AlarmItem>?
): Parcelable

//data class AlarmItem(val testText: String)