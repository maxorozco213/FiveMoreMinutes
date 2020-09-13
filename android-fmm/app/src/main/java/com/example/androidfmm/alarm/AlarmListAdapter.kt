package com.example.androidfmm.alarm

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfmm.R
import com.example.androidfmm.data.AlarmViewModel
import kotlinx.android.synthetic.main.alarm_list_item.view.*
import kotlinx.android.synthetic.main.fragment_create_alarm.view.*
import org.w3c.dom.Text
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset

class AlarmListAdapter(): RecyclerView.Adapter<AlarmListAdapter.AlarmViewHolder>() {

    private var alarmList = emptyList<AlarmItem>()

    class AlarmViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.alarm_list_item, parent, false)

        itemView.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_alarmListFragment_to_createAlarmFragment)
            Log.i("CLICKED", "THE VIEW")
        }

        return AlarmViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        val currentItem = alarmList[position]
        holder.itemView.alarmTime.text = currentItem.alarmName.toString()
//        holder.alarmCountView.value = currentItem.alarmCount
//        holder.alarmIntervalView.value = currentItem.alarmInterval
//        holder.alarmDateTimeView.toString() = currentItem.alarmDateTime
    }

    override fun getItemCount() = alarmList.size

    fun setData(alarm: List<AlarmItem>) {
        this.alarmList = alarm
        notifyDataSetChanged()
    }

//    class AlarmViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//        val alarmNameView: TextView = itemView.alarm_name
//        val alarmCountView: NumberPicker = itemView.alarm_count
//        val alarmIntervalView: NumberPicker = itemView.alarm_interval

//        val alarmDateTimeView: OffsetDateTime = OffsetDateTime.of(
//            LocalDateTime.of(
//                year = itemView.alarmTimePicker.,
//                month = month,
//                dayOfMonth = dayOfMonth,
//                hour = itemView.alarmTimePicker.hour,
//                minute = itemView.alarmTimePicker.minute,
//            ),
//            ZoneOffset.ofHoursMinutes(6, 30)
//        )

//    }
}