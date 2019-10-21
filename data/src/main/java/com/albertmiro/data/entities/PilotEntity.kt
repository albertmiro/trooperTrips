package com.albertmiro.data.entities

import com.google.gson.annotations.SerializedName

data class PilotEntity(
    @SerializedName("name")
    val name: String,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("rating")
    val rating: Double
)
