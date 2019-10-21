package com.albertmiro.data.mapper

import com.albertmiro.data.entities.DistanceEntity
import com.albertmiro.data.entities.LocationEntity
import com.albertmiro.data.entities.PilotEntity
import com.albertmiro.data.entities.TripEntity
import com.albertmiro.domain.models.Location
import com.albertmiro.domain.models.Pilot
import com.albertmiro.domain.models.Trip

class TripsMapper : Mapper {

    override fun toTripList(input: List<TripEntity>): List<Trip> =
        input.map { toTrip(it) }

    override fun toTrip(input: TripEntity): Trip {
        val id = input.id
        val distance = toDistance(input.distance)
        val pickUp = toLocation(input.pickUp)
        val dropOff = toLocation(input.dropOff)
        val duration = input.duration
        val pilot = toPilot(input.pilot)

        return Trip(id, pilot, distance, duration, pickUp, dropOff)
    }

    private fun toPilot(pilot: PilotEntity): Pilot =
        Pilot(pilot.name, pilot.avatar, pilot.rating)

    private fun toLocation(location: LocationEntity): Location =
        Location(location.name, location.picture, location.date)

    private fun toDistance(distance: DistanceEntity): String =
        "${distance.value} ${distance.unit}"

}