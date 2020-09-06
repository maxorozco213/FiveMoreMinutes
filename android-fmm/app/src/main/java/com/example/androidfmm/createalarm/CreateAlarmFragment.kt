package com.example.androidfmm.createalarm

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.androidfmm.R
import com.example.androidfmm.alarm.AlarmItem
import com.example.androidfmm.data.AlarmViewModel
import com.example.androidfmm.databinding.FragmentCreateAlarmBinding
import kotlinx.android.synthetic.main.fragment_create_alarm.*
import kotlinx.android.synthetic.main.fragment_create_alarm.view.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.properties.Delegates

class CreateAlarmFragment: Fragment() {
    private lateinit var mAlarmViewModel: AlarmViewModel

    // Allows variables to be access from anywhere in the application
    companion object {
        val alarmYearFromObject = 0
        val alarmMonthFromObject = 0
        val alarmDayFromObject = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentCreateAlarmBinding>(
            inflater, R.layout.fragment_create_alarm, container, false)
        binding.createAlarm = this

        mAlarmViewModel = ViewModelProvider(this).get(AlarmViewModel::class.java)

        val openDatePicker: Button = binding.openDatePicker
        val datePickerText: TextView = binding.alarmDate
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Set an initial date (current date on opening the app)
        datePickerText.text = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")).toString()

        // Open the date picker and reset the date to the chosen date
        openDatePicker.setOnClickListener {
            val datePickerDialog = DatePickerDialog(requireContext(), {
                _, mYear, mMonth, mDay -> datePickerText.text = LocalDate.of(mYear, mMonth, mDay)
                    .format(DateTimeFormatter.ofPattern("MM/dd/yyyy")).toString()
            }, year, month, day)

            datePickerDialog.show()
        }


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.actionbar_save, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.save_button -> {
            insertNewAlarmToDatabase()
            Log.i("MENU", "SAVE BUTTON")
            true
        }
        R.id.nav_host_fragment -> {
            NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                    || super.onOptionsItemSelected(item)
            true
        }
        else -> {
            Log.i("MENU", "WAT_CREATE")
            super.onOptionsItemSelected(item)
        }
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun insertNewAlarmToDatabase() {
        val alarmName = alarm_name.toString()
        val alarmCount = alarm_count.value.toInt()
        val alarmInterval = alarm_interval.value.toInt()
        val alarmDateTimeView = OffsetDateTime.of(
            LocalDateTime.of(
                alarmYearFromObject,
                alarmMonthFromObject,
                alarmDayFromObject,
                alarm_time_picker.hour,
                alarm_time_picker.minute
            ),
            ZoneOffset.ofHoursMinutes(6, 30)
        )

        if (inputCheck(alarmName)) {
            val alarmItem = AlarmItem(0, alarmName, alarmDateTimeView, alarmCount, alarmInterval)
            mAlarmViewModel.addAlarm(alarmItem)
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.alarmListFragment)
            view?.hideKeyboard()
            Toast.makeText(requireContext(), "New Alarm Group Created", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(requireContext(), "There was an Error", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(alarmName: String): Boolean {
        return !(TextUtils.isEmpty(alarmName))
    }
}