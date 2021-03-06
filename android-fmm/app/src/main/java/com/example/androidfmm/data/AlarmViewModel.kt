package com.example.androidfmm.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.androidfmm.alarm.AlarmItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlarmViewModel(application: Application): AndroidViewModel(application){
    val readAllData: LiveData<List<AlarmItem>>
    private val repository: AlarmRepository

    init {
        val alarmDao = AppDatabase.getDatabase(application).alarmDao()
        repository = AlarmRepository(alarmDao)
        readAllData = repository.readAllData
    }

    fun addAlarm(alarmItem: AlarmItem){
        viewModelScope.launch(Dispatchers.IO){
            repository.addAlarm(alarmItem)
        }
    }

    fun updateAlarm(alarmItem: AlarmItem) {
        viewModelScope.launch(Dispatchers.IO){
            repository.updateAlarm(alarmItem)
        }
    }

    fun deleteAlarm(alarmItem: AlarmItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAlarm(alarmItem)
        }
    }

    fun deleteAllAlarms() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllAlarms()
        }
    }
}