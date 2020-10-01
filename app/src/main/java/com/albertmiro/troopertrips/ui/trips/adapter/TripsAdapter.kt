package com.albertmiro.troopertrips.ui.trips.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.albertmiro.domain.models.Trip
import com.albertmiro.troopertrips.ui.base.adapter.ViewWrapper
import com.albertmiro.troopertrips.ui.trips.items.TripItemView
import com.albertmiro.troopertrips.ui.trips.items.TripItemViewWithRating
import com.albertmiro.troopertrips.ui.trips.items.TripItemViewWithoutRating

class TripsAdapter : ListAdapter<Trip, ViewWrapper<TripItemView>>(DiffCallback()) {

    enum class PilotType(val type: Int) { WithRating(0), WithoutRating(1) }

    var onClickAction: ((Trip) -> Unit)? = null

    private val items = emptyList<Trip>().toMutableList()

    fun setItems(newItems: List<Trip>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].pilot.rating > 0) {
            PilotType.WithRating.type
        } else {
            PilotType.WithoutRating.type
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewWrapper<TripItemView> {
        return ViewWrapper(
            onCreateItemView(
                parent,
                viewType
            )
        )
    }

    override fun onBindViewHolder(holder: ViewWrapper<TripItemView>, position: Int) {
        val trip = items[position]

        holder.view.apply {
            bind(trip)
        }

        holder.view.setOnClickListener {
            onClickAction?.invoke(items[position])
        }
    }

    override fun getItemCount() = items.size

    private fun onCreateItemView(parent: ViewGroup, viewType: Int): TripItemView {
        return when (viewType) {
            PilotType.WithRating.type -> TripItemViewWithRating(context = parent.context)
            else -> TripItemViewWithoutRating(context = parent.context)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Trip>() {
        override fun areItemsTheSame(oldItem: Trip, newItem: Trip): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Trip, newItem: Trip): Boolean {
            return oldItem == newItem
        }

    }
}