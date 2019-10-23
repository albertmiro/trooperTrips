package com.albertmiro.data.mapper

import com.albertmiro.data.entities.DistanceEntity
import com.albertmiro.data.entities.LocationEntity
import com.albertmiro.data.entities.PilotEntity
import com.albertmiro.data.entities.TripEntity
import com.albertmiro.domain.models.Location
import com.albertmiro.domain.models.Pilot
import com.albertmiro.domain.models.Trip
import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class TripsMapper : Mapper {

    override fun toTripList(input: List<TripEntity>): List<Trip> =
        input.map { toTrip(it) }

    override fun toTrip(input: TripEntity): Trip {
        val id = input.id
        val distance = toDistance(input.distance)
        val pickUp = toLocation(input.pickUp)
        val dropOff = toLocation(input.dropOff)
        val duration = parseTimeInMillis(input.duration)
        val pilot = toPilot(input.pilot)

        return Trip(id, pilot, distance, duration, pickUp, dropOff)
    }

    private fun toPilot(pilot: PilotEntity): Pilot =
        Pilot(pilot.name, pilot.avatar, pilot.rating)

    private fun toLocation(location: LocationEntity): Location =
        Location(location.name, location.picture, parseDate(location.date))

    private fun toDistance(distance: DistanceEntity): String {
        val formatter = DecimalFormat("#,###,###")
        val distanceFormatted = formatter.format(distance.value)
        return "$distanceFormatted ${distance.unit}"
    }

    private fun parseDate(date: String): String {
        val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val output = SimpleDateFormat("K:mm a", Locale.getDefault())
        val formattedDate: Date?
        try {
            formattedDate = input.parse(date)
        } catch (e: ParseException) {
            return "--:--"
        }

        return output.format(formattedDate!!)
    }

    private fun parseTimeInMillis(timeInMillis: Long): String {
        val time = Date(timeInMillis)
        val formatter = SimpleDateFormat("H:mm:ss", Locale.getDefault())
        var formattedTime: String?
        try {
            formattedTime = formatter.format(time)
        } catch (e: ParseException) {
            return "--:--:--"
        }
        return formattedTime
    }

}