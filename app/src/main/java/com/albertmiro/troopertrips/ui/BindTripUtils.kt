package com.albertmiro.troopertrips.ui

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.albertmiro.domain.models.Trip
import com.albertmiro.troopertrips.R

object BindTripUtils {

    fun bindTrip(
        vehicle: Trip,
        vehicleHeader: TextView,
        vehicleDescription: TextView,
        vehicleImage: ImageView
    ) {
        val vehicleCapacity = (2).toLong()
        val drawableId = R.drawable.ic_launcher_background

        vehicleHeader.text = getFormattedString(
            vehicleHeader.context,
            R.string.app_name,
            vehicle.id
        )

        vehicleDescription.text = getFormattedString(
            vehicleDescription.context,
            R.string.app_name,
            vehicleCapacity
        )
        vehicleImage.setImageResource(drawableId)
    }

    private fun getFormattedString(context: Context, stringId: Int, value: Long): String =
        String.format(context.getString(stringId), value)
}