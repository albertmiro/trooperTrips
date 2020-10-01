package com.albertmiro.troopertrips.ui.trips.items

import android.content.Context
import android.widget.RelativeLayout
import com.albertmiro.domain.models.Trip

open class TripItemView constructor(context: Context) : RelativeLayout(context) {
    open fun bind(trip: Trip) {}
}
