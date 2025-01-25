package com.bjit.alarmmanager

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var alarmAdapter: AlarmAdapter
    private val alarms = mutableListOf<Alarm>()
    private var alarmIdCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerViewAlarms: RecyclerView = findViewById(R.id.recyclerViewAlarms)
        val buttonNewAlarm: Button = findViewById(R.id.buttonNewAlarm)

        alarmAdapter = AlarmAdapter(alarms, this::editAlarm, this::deleteAlarm)
        recyclerViewAlarms.adapter = alarmAdapter
        recyclerViewAlarms.layoutManager = LinearLayoutManager(this)

        buttonNewAlarm.setOnClickListener {
            createNewAlarm()
        }
    }

    private fun createNewAlarm() {
        val timePicker = TimePicker(this)
        AlertDialog.Builder(this)
            .setTitle("Set Alarm Time")
            .setView(timePicker)
            .setPositiveButton("Set") { _, _ ->
                val hour = timePicker.hour
                val minute = timePicker.minute
                val alarm = Alarm(alarmIdCounter++, hour, minute, true)
                alarms.add(alarm)
                alarmAdapter.notifyDataSetChanged()
                saveAlarmToDatabase(alarm)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun editAlarm(alarm: Alarm) {
        val timePicker = TimePicker(this)
        timePicker.hour = alarm.hour
        timePicker.minute = alarm.minute
        AlertDialog.Builder(this)
            .setTitle("Edit Alarm Time")
            .setView(timePicker)
            .setPositiveButton("Update") { _, _ ->
                alarm.hour = timePicker.hour
                alarm.minute = timePicker.minute
                alarmAdapter.notifyDataSetChanged()
                updateAlarmInDatabase(alarm)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun deleteAlarm(alarm: Alarm) {
        AlertDialog.Builder(this)
            .setMessage("Are you sure you want to delete this alarm?")
            .setPositiveButton("Yes") { _, _ ->
                alarms.remove(alarm)
                alarmAdapter.notifyDataSetChanged()
                deleteAlarmFromDatabase(alarm)
            }
            .setNegativeButton("No", null)
            .show()
    }

    private fun saveAlarmToDatabase(alarm: Alarm) {
        // Implement database save logic here
    }

    private fun updateAlarmInDatabase(alarm: Alarm) {
        // Implement database update logic here
    }

    private fun deleteAlarmFromDatabase(alarm: Alarm) {
        // Implement database delete logic here
    }
}