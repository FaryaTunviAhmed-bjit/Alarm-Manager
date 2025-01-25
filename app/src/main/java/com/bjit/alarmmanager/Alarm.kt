package com.bjit.alarmmanager

data class Alarm(
    val id: Int,
    var hour: Int,
    var minute: Int,
    var isEnabled: Boolean
)
