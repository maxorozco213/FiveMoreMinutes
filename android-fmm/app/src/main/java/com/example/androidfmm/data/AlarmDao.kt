package com.example.androidfmm.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.androidfmm.alarm.AlarmItem

@Dao
interface AlarmDao {
    @Query("SELECT * FROM alarm_table")
    fun getAllAlarms(): LiveData<List<AlarmItem>>

    @Insert
    fun addAlarm(vararg alarm: AlarmItem)

    @Delete
    fun delete(alarm: AlarmItem)

    @Update
    suspend fun updateAlarm(alarm: AlarmItem)
}