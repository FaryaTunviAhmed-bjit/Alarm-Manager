package com.bjit.alarmmanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MainActivityTest {

    @Test
    public void createNewAlarm_addsAlarmToList() {
        MainActivity activity = new MainActivity();
        activity.createNewAlarm(8, 30);
        assertEquals(1, activity.getAlarms().size());
        assertEquals(8, activity.getAlarms().get(0).getHour());
        assertEquals(30, activity.getAlarms().get(0).getMinute());
    }

    @Test
    public void editAlarm_updatesAlarmTime() {
        MainActivity activity = new MainActivity();
        Alarm alarm = new Alarm(1, 7, 45, true);
        activity.getAlarms().add(alarm);
        activity.editAlarm(alarm, 9, 15);
        assertEquals(9, alarm.getHour());
        assertEquals(15, alarm.getMinute());
    }

    @Test
    public void deleteAlarm_removesAlarmFromList() {
        MainActivity activity = new MainActivity();
        Alarm alarm = new Alarm(1, 7, 45, true);
        activity.getAlarms().add(alarm);
        activity.deleteAlarm(alarm);
        assertTrue(activity.getAlarms().isEmpty());
    }

    @Test
    public void createNewAlarm_doesNotAddAlarmWhenCancelled() {
        MainActivity activity = new MainActivity();
        activity.createNewAlarm(8, 30, false);
        assertTrue(activity.getAlarms().isEmpty());
    }

    @Test
    public void editAlarm_doesNotUpdateAlarmWhenCancelled() {
        MainActivity activity = new MainActivity();
        Alarm alarm = new Alarm(1, 7, 45, true);
        activity.getAlarms().add(alarm);
        activity.editAlarm(alarm, 9, 15, false);
        assertEquals(7, alarm.getHour());
        assertEquals(45, alarm.getMinute());
    }

    @Test
    public void deleteAlarm_doesNotRemoveAlarmWhenCancelled() {
        MainActivity activity = new MainActivity();
        Alarm alarm = new Alarm(1, 7, 45, true);
        activity.getAlarms().add(alarm);
        activity.deleteAlarm(alarm, false);
        assertEquals(1, activity.getAlarms().size());
    }
}