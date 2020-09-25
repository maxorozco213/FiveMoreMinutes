package com.example.androidfmm.data

import androidx.lifecycle.LiveData
import com.example.androidfmm.alarm.AlarmItem

class AlarmRepository(private val alarmDao: AlarmDao){
    val readAllData: LiveData<List<AlarmItem>> = alarmDao.getAllAlarms()

    suspend fun addAlarm(alarmItem: AlarmItem) {
        alarmDao.addAlarm(alarmItem)
    }

    suspend fun updateAlarm(alarmItem: AlarmItem) {
        alarmDao.updateAlarm(alarmItem)
    }

    suspend fun deleteAlarm(alarmItem: AlarmItem) {
        alarmDao.deleteAlarm(alarmItem)
    }

    suspend fun deleteAllAlarms() {
        alarmDao.deleteAllAlarms()
    }
}