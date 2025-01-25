package com.bjit.alarmmanager;

package com.bjit.alarmmanager

import static org.junit.Assert.assertEquals;

import org.junit.Test
import org.junit.Assert.*

class MainActivityTest {

    @Test
    fun createNewAlarm_addsAlarmToList() {
        val activity = MainActivity()
        activity.createNewAlarm(8, 30)
        assertEquals(1, activity.alarms.size)
        assertEquals(8, activity.alarms[0].hour)
        assertEquals(30, activity.alarms[0].minute)
    }

    @Test
    fun editAlarm_updatesAlarmTime() {
        val activity = MainActivity()
        val alarm = Alarm(1, 7, 45, true)
        activity.alarms.add(alarm)
        activity.editAlarm(alarm, 9, 15)
        assertEquals(9, alarm.hour)
        assertEquals(15, alarm.minute)
    }

    @Test
    fun deleteAlarm_removesAlarmFromList() {
        val activity = MainActivity()
        val alarm = Alarm(1, 7, 45, true)
        activity.alarms.add(alarm)
        activity.deleteAlarm(alarm)
        assertTrue(activity.alarms.isEmpty())
    }

    @Test
    fun createNewAlarm_doesNotAddAlarmWhenCancelled() {
        val activity = MainActivity()
        activity.createNewAlarm(8, 30, false)
        assertTrue(activity.alarms.isEmpty())
    }

    @Test
    fun editAlarm_doesNotUpdateAlarmWhenCancelled() {
        val activity = MainActivity()
        val alarm = Alarm(1, 7, 45, true)
        activity.alarms.add(alarm)
        activity.editAlarm(alarm, 9, 15, false)
        assertEquals(7, alarm.hour)
        assertEquals(45, alarm.minute)
    }

    @Test
    fun deleteAlarm_doesNotRemoveAlarmWhenCancelled() {
        val activity = MainActivity()
        val alarm = Alarm(1, 7, 45, true)
        activity.alarms.add(alarm)
        activity.deleteAlarm(alarm, false)
        assertEquals(1, activity.alarms.size)
    }
}
