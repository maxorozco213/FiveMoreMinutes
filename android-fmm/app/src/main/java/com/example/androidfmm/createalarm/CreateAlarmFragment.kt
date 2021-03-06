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

    // Allows variables to be access from anywhere in this fragment (Since it's private)
    private companion object {
        var alarmYearFromObject = 0
        var alarmMonthFromObject = 0
        var alarmDayFromObject = 0
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

        val openDatePicker: ImageButton = binding.openDatePicker
        val datePickerText: TextView = binding.alarmDate
        val calendar = Calendar.getInstance()

        val year = calendar.get(Calendar.YEAR)
        // Month is returned as an ??index?? so 1 needs to be added to get the correct month number value
        val month = calendar.get(Calendar.MONTH)+1
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        alarmMonthFromObject = month-1
        alarmMonthFromObject = month
        alarmDayFromObject = day

        // Set an initial date (current date on opening the app)
        datePickerText.text = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")).toString()

        // Open the date picker and reset the date to the chosen date
        openDatePicker.setOnClickListener {
            val datePickerDialog = DatePickerDialog(requireContext(), {
                _, mYear, mMonth, mDay -> datePickerText.text = LocalDate.of(mYear, mMonth, mDay)
                    .format(DateTimeFormatter.ofPattern("MM/dd/yyyy")).toString()

                // Set values to the global objects
                alarmYearFromObject = mYear
                alarmMonthFromObject = mMonth
                alarmDayFromObject = mDay

                // Log the change
                Log.i("DATELOG",
                    ("year $alarmYearFromObject month $alarmMonthFromObject day $alarmDayFromObject").toString()
                )
            }, year, month, day)

            datePickerDialog.show()
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.actionbar_save, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }

    // Determines the ID of the option that was clicked
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

    // Called after insert to database
    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun insertNewAlarmToDatabase() {
        val alarmName = alarm_name_input.text.toString()
        val alarmCount = alarm_count.value
        val alarmInterval = alarm_interval_input.value
        // Creates the alarm DateTime object
        val alarmDateTimeView = OffsetDateTime.of(
            LocalDateTime.of(
                alarmYearFromObject,
                alarmMonthFromObject-1,
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
        } else if (!inputCheck(alarmName)) {
            Toast.makeText(requireContext(), "20 character limit for alarm name", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(requireContext(), "There was an error creating the alarm", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(alarmName: String): Boolean {
        if (alarmName.length > 20) {
            return false
        }
        return true
    }
}