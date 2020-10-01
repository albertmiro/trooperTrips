package com.albertmiro.troopertrips.ui.base.adapter

import androidx.recyclerview.widget.RecyclerView
import com.albertmiro.troopertrips.ui.trips.items.TripItemView

class ViewWrapper<out V : TripItemView>(val view: V) : RecyclerView.ViewHolder(view)