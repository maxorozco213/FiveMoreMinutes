package com.example.androidfmm.alarm

import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.TextView
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfmm.R
import com.example.androidfmm.createalarm.UpdateAlarmFragment
import com.example.androidfmm.data.AlarmViewModel
import kotlinx.android.synthetic.main.alarm_list_item.view.*
import kotlinx.android.synthetic.main.fragment_create_alarm.view.*
import org.w3c.dom.Text
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

// Adapter for the alarm item, gives data to the UI
class AlarmListAdapter(): RecyclerView.Adapter<AlarmListAdapter.AlarmViewHolder>() {

    private var alarmList = emptyList<AlarmItem>()

    class AlarmViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.alarm_list_item, parent, false)

//        itemView.setOnClickListener {view: View ->
//            view.findNavController().navigate(R.id.action_alarmListFragment_to_updateAlarmFragment)
//            Log.i("CLICKED", "THE VIEW")
//        }

        return AlarmViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        val currentItem = alarmList[position]
        val interval = currentItem.alarmInterval.toString()
        holder.itemView.alarm_name.text = currentItem.alarmName
        // Get main time display for list items
        holder.itemView.alarm_time.text = currentItem.alarmDateTime.toLocalTime()
            .format(DateTimeFormatter.ofPattern("h:mm")).toString()
        // Get the schedule of the alarm - Can be a single date or multiple days in a week repeating each week
        holder.itemView.alarm_schedule.text = currentItem.alarmDateTime.toLocalDate()
            .plusYears(2020).plusMonths(1)
            .format(DateTimeFormatter.ofPattern("M/d/yy")).toString()
        // Get the interval, will also need the total count
        holder.itemView.alarm_interval.text = if (interval != "0") interval else "Single alarm"

        // Navigate and send alarm object to Update Fragment
        holder.itemView.alarm_list_item.setOnClickListener {
            val action = AlarmListFragmentDirections.actionAlarmListFragmentToUpdateAlarmFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = alarmList.size

    fun setData(alarm: List<AlarmItem>) {
        this.alarmList = alarm
        notifyDataSetChanged()
    }
}