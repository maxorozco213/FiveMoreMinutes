package com.example.androidfmm.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.androidfmm.alarm.AlarmItem

@Dao
interface AlarmDao {
    @Query("SELECT * FROM alarm_table")
    fun getAllAlarms(): LiveData<List<AlarmItem>>

    @Insert
    suspend fun addAlarm(vararg alarm: AlarmItem)

    @Delete
    suspend fun deleteAlarm(alarm: AlarmItem)

    @Query("DELETE FROM alarm_table")
    suspend fun deleteAllAlarms()

    @Update
    suspend fun updateAlarm(alarm: AlarmItem)
}