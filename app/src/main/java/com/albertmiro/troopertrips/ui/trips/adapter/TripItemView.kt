package com.albertmiro.troopertrips.ui.trips.adapter

import android.content.Context
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.albertmiro.domain.models.Trip
import com.albertmiro.troopertrips.R
import com.albertmiro.troopertrips.ui.BindTripUtils
import kotlinx.android.synthetic.main.item_trip_avatar.view.*
import kotlinx.android.synthetic.main.item_trip_details.view.*


class TripItemView constructor(context: Context) : RelativeLayout(context) {
    init {
        LayoutInflater.from(context).inflate(R.layout.item_trip_without_rating, this, true)
    }

    fun bind(trip: Trip) {
        BindTripUtils.bindTripItem(trip, avatar, pilotName, pickUpLocation, dropOffLocation)
    }

}