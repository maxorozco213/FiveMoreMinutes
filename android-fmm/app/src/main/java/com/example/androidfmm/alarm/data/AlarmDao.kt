package com.example.androidfmm.alarm.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.androidfmm.alarm.AlarmItem

@Dao
interface AlarmDao {
    @Query("SELECT * FROM alarm_table")
    fun getAllAlarms(): LiveData<List<AlarmItem>>

    @Insert
    fun addAlarm(vararg alarm: AlarmItem)

    @Delete
    fun delete(user: AlarmItem)
}