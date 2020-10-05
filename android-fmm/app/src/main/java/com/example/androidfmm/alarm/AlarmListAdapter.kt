package com.example.androidfmm.alarm

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfmm.R
import kotlinx.android.synthetic.main.alarm_list_item.view.*
import java.time.format.DateTimeFormatter

// Adapter for the alarm item, gives data to the UI
class AlarmListAdapter(context: Context?, private var itemSelectedListener: ItemSelectedListener): RecyclerView.Adapter<AlarmListAdapter.AlarmViewHolder>() {
    private var alarmList = emptyList<AlarmItem>()

    interface ItemSelectedListener {
        fun onItemSelected(item:Any)
    }
    
    inner class AlarmViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        init {
            itemView.alarm_switch.setOnClickListener { itemSelectedListener.onItemSelected(alarmList[adapterPosition]) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.alarm_list_item, parent, false)

        return AlarmViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        val currentItem = alarmList[position]
        val interval = currentItem.alarmInterval.toString()

        holder.itemView.alarm_name.text = currentItem.alarmName
        // Get main time display for list items
        holder.itemView.alarm_time.text = currentItem.alarmDateTime.toLocalTime()
            .format(DateTimeFormatter.ofPattern("h:mma")).toString()
        // Get the schedule of the alarm - Can be a single date or multiple days in a week repeating each week
        holder.itemView.alarm_schedule.text = currentItem.alarmDateTime.toLocalDate()
            .plusYears(2020).plusMonths(1)
            .format(DateTimeFormatter.ofPattern("M/d/yy")).toString()
        // Get the interval, will also need the total count
        holder.itemView.alarm_interval.text = if (interval != "0") interval else "Single alarm"
        holder.itemView.alarm_switch.isChecked = currentItem.isActive

        // Set the new bool to
        holder.itemView.alarm_switch.setOnCheckedChangeListener { _, _ ->
            Log.i("ACTIVE", currentItem.isActive.toString())
        }

        // Navigate and send alarm object to Update Fragment
        holder.itemView.alarm_list_item.setOnClickListener {
            val action = AlarmListFragmentDirections.actionAlarmListFragmentToUpdateAlarmFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

        holder.itemView.alarm_list_item.setOnLongClickListener {
            Log.i("LONG", "PRESS")
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount() = alarmList.size

    fun setData(alarm: List<AlarmItem>) {
        this.alarmList = alarm
        notifyDataSetChanged()
    }
}