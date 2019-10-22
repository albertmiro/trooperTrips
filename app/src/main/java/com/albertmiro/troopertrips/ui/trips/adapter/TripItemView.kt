package com.albertmiro.troopertrips.ui.trips.adapter

import android.content.Context
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.albertmiro.domain.models.Trip
import com.albertmiro.troopertrips.R
import com.albertmiro.troopertrips.ui.BindTripUtils
import kotlinx.android.synthetic.main.item_trip.view.*


class TripItemView constructor(context: Context) : RelativeLayout(context) {
    init {
        LayoutInflater.from(context).inflate(R.layout.item_trip, this, true)
    }

    fun bind(trip: Trip) {
        BindTripUtils.bindTrip(trip, avatar, pilotName, pickUpLocation, dropOffLocation)
    }

}