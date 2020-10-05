package com.example.androidfmm.createalarm

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.example.androidfmm.R
import com.example.androidfmm.alarm.AlarmItem
import com.example.androidfmm.alarm.AlarmListAdapter
import com.example.androidfmm.data.AlarmViewModel
import com.example.androidfmm.databinding.FragmentUpdateAlarmBinding
import kotlinx.android.synthetic.main.fragment_update_alarm.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

class UpdateAlarmFragment: Fragment() {
    private lateinit var mAlarmViewModel: AlarmViewModel
    // See app:gradle lines 33-40
    private val args by navArgs<UpdateAlarmFragmentArgs>()

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
        val binding = DataBindingUtil.inflate<FragmentUpdateAlarmBinding>(
            inflater, R.layout.fragment_update_alarm, container, false)
        binding.updateAlarm = this

        mAlarmViewModel = ViewModelProvider(this).get(AlarmViewModel::class.java)

        // Declare variables bound to UI elements
        val openDatePicker: ImageButton = binding.openDatePicker
        val datePickerText: TextView = binding.updateAlarmDate
        val timePickerValue: TimePicker = binding.updateAlarmTimePicker
        val selectedAlarmName: TextView = binding.updateAlarmNameInput

        // Declare time and date variables
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
            // Month is returned as an ??index?? so 1 needs to be added to get the correct month number value
        val month = calendar.get(Calendar.MONTH)+1
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Store in variables that can be used by the rest of the fragment
        alarmMonthFromObject = month-1
        alarmMonthFromObject = month
        alarmDayFromObject = day

        // Set an initial values from selected item
        timePickerValue.hour = args.currentAlarm.alarmDateTime.toLocalTime().hour
        timePickerValue.minute = args.currentAlarm.alarmDateTime.toLocalTime().minute
        selectedAlarmName.text = args.currentAlarm.alarmName

        val currentDate = args.currentAlarm.alarmDateTime.toLocalDate()
            .plusMonths(1).plusYears(2020)
            .format(DateTimeFormatter.ofPattern("M/dd/yyyy"))
        datePickerText.text = currentDate.toString()

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

    // Inflate the menu options
    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.actionbar_update, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }

    // Determines the ID of the option that was clicked
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.update_save_button -> {
            updateAlarm()
            Log.i("MENU", "SAVE BUTTON")
            true
        }
        // Not sure what this is
        R.id.nav_host_fragment -> {
            NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                    || super.onOptionsItemSelected(item)
            Log.i("NAVHOST", "HUH")
            true
        }
        // Deletes the current alarm from the editing page
        R.id.update_delete_button -> {
            // Delete the current alarm and navigate back to the list view
            deleteAlarm()
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

    private fun updateAlarm() {
        val alarmID = args.currentAlarm.id
        val alarmName = update_alarm_name_input.text.toString()
        val alarmCount = update_alarm_count.value
        val alarmInterval = update_alarm_interval_input.value
        val isActive = true
        // Creates the alarm DateTime object
        // I don't really understand OffsetDateTime completely...
        val alarmDateTimeView = OffsetDateTime.of(
            LocalDateTime.of(
                alarmYearFromObject,
                alarmMonthFromObject-1,
                alarmDayFromObject,
                update_alarm_time_picker.hour,
                update_alarm_time_picker.minute
            ),
            ZoneOffset.ofHoursMinutes(6, 30)
        )

        if (inputCheck(alarmName)) {
            // Obviously have to include the specific ID in an update
            val alarmItem = AlarmItem(alarmID, alarmName, alarmDateTimeView, alarmCount, alarmInterval, isActive)

            mAlarmViewModel.updateAlarm(alarmItem)
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.alarmListFragment)
            view?.hideKeyboard()
            Toast.makeText(requireContext(), "Alarm group updated", Toast.LENGTH_LONG).show()
        } else if (!inputCheck(alarmName)) {
            Toast.makeText(requireContext(), "20 character limit for alarm name", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(requireContext(), "There was an error creating the alarm", Toast.LENGTH_LONG).show()
        }
    }

    private fun deleteAlarm() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("CONFIRM") { _, _ ->
            mAlarmViewModel.deleteAlarm(args.currentAlarm)
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.alarmListFragment)
            Toast.makeText(requireContext(), "Alarm deleted", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("NO") { _, _ -> }
        builder.setTitle(args.currentAlarm.alarmName)
        builder.setMessage("Are you sure you want to delete this alarm?")
        builder.create().show()
    }

    private fun inputCheck(alarmName: String): Boolean {
        if (alarmName.length > 20) {
            return false
        }

        return true
    }
}