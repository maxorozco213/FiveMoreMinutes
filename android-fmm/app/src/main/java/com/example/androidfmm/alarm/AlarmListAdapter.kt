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
import java.time.format.DateTimeFormatter

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
        holder.itemView.alarm_name.text = currentItem.alarmName
        holder.itemView.alarm_time.text = currentItem.alarmDateTime.toLocalTime()
            .format(DateTimeFormatter.ofPattern("hh:mm"))
            .toString()
    }

    override fun getItemCount() = alarmList.size

    fun setData(alarm: List<AlarmItem>) {
        this.alarmList = alarm
        notifyDataSetChanged()
    }
}