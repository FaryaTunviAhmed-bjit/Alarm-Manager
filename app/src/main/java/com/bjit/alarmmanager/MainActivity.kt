package com.bjit.alarmmanager

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * MainActivity class that handles the creation, editing, and deletion of alarms.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var alarmAdapter: AlarmAdapter
    private val alarms = mutableListOf<Alarm>()
    private var alarmIdCounter = 0

    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     */
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

    /**
     * Creates a new alarm by showing a TimePicker dialog.
     */
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

    /**
     * Edits an existing alarm by showing a TimePicker dialog.
     * @param alarm The alarm to be edited.
     */
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

    /**
     * Deletes an existing alarm by showing a confirmation dialog.
     * @param alarm The alarm to be deleted.
     */
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

    /**
     * Saves the alarm to the database.
     * @param alarm The alarm to be saved.
     */
    private fun saveAlarmToDatabase(alarm: Alarm) {
        // Implement database save logic here
    }

    /**
     * Updates the alarm in the database.
     * @param alarm The alarm to be updated.
     */
    private fun updateAlarmInDatabase(alarm: Alarm) {
        // Implement database update logic here
    }

    /**
     * Deletes the alarm from the database.
     * @param alarm The alarm to be deleted.
     */
    private fun deleteAlarmFromDatabase(alarm: Alarm) {
        // Implement database delete logic here
    }
}