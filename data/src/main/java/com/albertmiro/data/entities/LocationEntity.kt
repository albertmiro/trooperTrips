package com.albertmiro.data.entities

import com.google.gson.annotations.SerializedName

data class LocationEntity(
    @SerializedName("name")
    val name: String,
    @SerializedName("picture")
    val picture: String,
    @SerializedName("date")
    val date: String
)
