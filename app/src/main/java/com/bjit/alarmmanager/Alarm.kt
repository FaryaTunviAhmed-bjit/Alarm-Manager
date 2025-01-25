package com.bjit.alarmmanager

data class Alarm(
    val id: Int,
    var timeInMillis: Long,
    var label: String,
    var isRepeating: Boolean
)
