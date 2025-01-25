package com.bjit.alarmmanager

import android.app.AlarmManager
import android.app.AlertDialog
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

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
        // Show dialog or new activity to create a new alarm
        // Add the new alarm to the list and notify the adapter
    }

    private fun editAlarm(alarm: Alarm) {
        // Show dialog or new activity to edit the alarm
        // Update the alarm in the list and notify the adapter
    }

    private fun deleteAlarm(alarm: Alarm) {
        AlertDialog.Builder(this)
            .setMessage("Are you sure you want to delete this alarm?")
            .setPositiveButton("Yes") { _, _ ->
                alarms.remove(alarm)
                alarmAdapter.notifyDataSetChanged()
            }
            .setNegativeButton("No", null)
            .show()
    }
}