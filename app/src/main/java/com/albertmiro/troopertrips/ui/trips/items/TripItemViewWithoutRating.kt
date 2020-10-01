package com.albertmiro.troopertrips.ui.trips.items

import android.content.Context
import android.view.LayoutInflater
import com.albertmiro.domain.models.Trip
import com.albertmiro.troopertrips.R
import com.albertmiro.troopertrips.ui.BindTripUtils
import kotlinx.android.synthetic.main.item_trip_avatar.view.*
import kotlinx.android.synthetic.main.item_trip_details.view.*


class TripItemViewWithoutRating constructor(context: Context) : TripItemView(context) {
    init {
        LayoutInflater.from(context).inflate(R.layout.item_trip_without_rating, this, true)
    }

    override fun bind(trip: Trip) {
        BindTripUtils.bindTripItem(trip, avatar, pilotName, pickUpLocation, dropOffLocation)
    }

}