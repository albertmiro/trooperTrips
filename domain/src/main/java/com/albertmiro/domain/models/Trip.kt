package com.albertmiro.domain.models

data class Trip(
    val id: Long,
    val pilot: Pilot,
    val distance: String,
    val duration: String,
    val pickUp: Location,
    val dropOff: Location
)