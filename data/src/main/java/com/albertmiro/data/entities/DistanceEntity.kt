package com.albertmiro.data.entities

import com.google.gson.annotations.SerializedName

data class DistanceEntity(
    @SerializedName("value")
    val value: Long,
    @SerializedName("unit")
    val unit: String
)
