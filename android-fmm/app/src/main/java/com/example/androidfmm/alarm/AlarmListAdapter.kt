package com.example.androidfmm.alarm

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfmm.R
import kotlinx.android.synthetic.main.alarm_list_item.view.*
import kotlinx.android.synthetic.main.fragment_create_alarm.view.*
import org.w3c.dom.Text

class AlarmListAdapter(private val alarmList: List<AlarmItem>): RecyclerView.Adapter<AlarmListAdapter.AlarmViewHolder>() {
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
        holder.alarmNameView.text = currentItem.alarmName

//        holder.testTextHold.text = currentItem.testText1
//        holder.alarmTimeView.text = currentItem.alarmTime.toString()
//        holder.alarmDateView.text = currentItem.alarmDate.toString()
//        holder.alarmDayOfWeekView.text = currentItem.dayOfWeek
//        holder.alarmCount.int = currentItem.alarmCount
    }

    override fun getItemCount() = alarmList.size

    class AlarmViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val alarmNameView: TextView = itemView.alarmName

//        val alarmDayOfWeekView: TextView = itemView.dayOfWeek
//        val alarmTimeView: TextView = itemView.alarmTime
//        val alarmDateView: TextView = itemView.alarmDate
//        val alarmWeekDayView: TextView = itemView.dayOfWeek
//        val alarmCount: TextView = itemView.alarmCount

    }
}