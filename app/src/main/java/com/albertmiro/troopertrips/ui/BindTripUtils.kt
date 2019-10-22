package com.albertmiro.troopertrips.ui

import android.widget.ImageView
import android.widget.TextView
import com.albertmiro.domain.models.Trip
import com.albertmiro.troopertrips.R
import com.bumptech.glide.Glide

object BindTripUtils {

    fun bindTrip(
        trip: Trip,
        avatarImage: ImageView,
        piltoName: TextView,
        pickUpLocation: TextView,
        dropOffLocation: TextView
    ) {
        val avatarPath = getImagePath(trip.pilot.avatar)
        piltoName.text = trip.pilot.name
        pickUpLocation.text = trip.pickUp.name
        dropOffLocation.text = trip.dropOff.name
        Glide.with(avatarImage.context)
            .load(avatarPath)
            .placeholder(R.drawable.user_placeholder)
            .into(avatarImage)

    }

    fun getImagePath(partialUrl: String): String {
        val baseImageUrl = "https://starwars.kapten.com"
        return "$baseImageUrl$partialUrl"
    }

}