package com.albertmiro.domain.repository

import com.albertmiro.domain.models.Trip
import io.reactivex.Single

interface TripsRepository {
    fun getTrips(foreRefresh: Boolean = false): Single<List<Trip>>
    fun getTripDetail(id: Int): Single<Trip>
}