package com.albertmiro.data.mapper

import com.albertmiro.data.entities.TripEntity
import com.albertmiro.domain.models.Trip

interface Mapper {
    fun toTripList(input: List<TripEntity>): List<Trip>
    fun toTrip(input: TripEntity): Trip
}