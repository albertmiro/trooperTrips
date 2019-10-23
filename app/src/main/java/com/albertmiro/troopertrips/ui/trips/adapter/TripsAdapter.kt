package com.albertmiro.troopertrips.ui.trips.adapter

import android.view.ViewGroup
import com.albertmiro.domain.models.Trip
import com.albertmiro.troopertrips.ui.base.adapter.RecyclerViewAdapterBase
import com.albertmiro.troopertrips.ui.base.adapter.ViewWrapper

class TripsAdapter : RecyclerViewAdapterBase<Trip, TripItemView>() {

    var onClickAction: ((Trip) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int) =
        TripItemView(context = parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<TripItemView>, position: Int) {
        val trip = items[position]

        holder.view.apply {
            bind(trip)
        }

        holder.view.setOnClickListener {
            onClickAction?.invoke(items[position])
        }
    }
}