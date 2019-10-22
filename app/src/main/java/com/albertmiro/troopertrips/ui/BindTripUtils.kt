package com.albertmiro.troopertrips.ui

import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.albertmiro.domain.models.Trip
import com.albertmiro.troopertrips.R
import com.bumptech.glide.Glide

object BindTripUtils {

    fun bindTrip(
        trip: Trip,
        avatarImage: ImageView,
        pilotName: TextView,
        pickUpLocation: TextView,
        dropOffLocation: TextView
    ) {
        val avatarPath = getImagePath(trip.pilot.avatar)
        pilotName.text = trip.pilot.name
        pickUpLocation.text = trip.pickUp.name
        dropOffLocation.text = trip.dropOff.name
        Glide.with(avatarImage.context)
            .load(avatarPath)
            .placeholder(R.drawable.user_placeholder)
            .into(avatarImage)
    }

    private fun getImagePath(partialUrl: String): String {
        val baseImageUrl = "https://starwars.kapten.com"
        return "$baseImageUrl$partialUrl"
    }

    fun bindDetails(
        trip: Trip,
        avatarImage: ImageView,
        pilotName: TextView,
        pickUpPlanetImage :ImageView,
        pickUpPlanetName: TextView,
        pickUpPlanetTime: TextView,
        dropOffPlanetImage :ImageView,
        dropOffPlanetName: TextView,
        dropOffPlanetTime: TextView,
        tripDistance: TextView,
        tripDuration: TextView,
        ratingBar: RatingBar
    ) {
        Glide.with(avatarImage.context)
            .load(getImagePath(trip.pilot.avatar))
            .placeholder(R.drawable.user_placeholder)
            .into(avatarImage)

        Glide.with(pickUpPlanetImage.context)
            .load(getImagePath(trip.pickUp.picture))
            .override(500,500)
            .into(pickUpPlanetImage)

        Glide.with(dropOffPlanetImage.context)
            .load(getImagePath(trip.dropOff.picture))
            .override(500,500)
            .into(dropOffPlanetImage)

        pilotName.text = trip.pilot.name
        pickUpPlanetName.text = trip.pickUp.name
        pickUpPlanetTime.text = trip.pickUp.date
        dropOffPlanetName.text = trip.dropOff.name
        dropOffPlanetTime.text = trip.dropOff.date
        tripDistance.text = trip.distance
        tripDuration.text = trip.duration.toString()
        ratingBar.rating = trip.pilot.rating.toFloat()
    }
}