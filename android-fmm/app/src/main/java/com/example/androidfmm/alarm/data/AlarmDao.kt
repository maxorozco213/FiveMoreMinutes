package com.example.androidfmm.alarm.data

import androidx.room.Dao
import androidx.room.Query
import com.example.androidfmm.alarm.AlarmItem

@Dao
interface AlarmDao {
    @Query("SELECT * FROM alarm_table")
    fun getAllAlarms(): List<AlarmItem>
}