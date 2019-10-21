package com.albertmiro.data.entities

import com.google.gson.annotations.SerializedName

data class TripEntity(
    @SerializedName("id")
    val id: Long,
    @SerializedName("pilot")
    val pilot: PilotEntity,
    @SerializedName("distance")
    val distance: DistanceEntity,
    @SerializedName("duration")
    val duration: Long,
    @SerializedName("pick_up")
    val pickUp: LocationEntity,
    @SerializedName("drop_off")
    val dropOff: LocationEntity
)

