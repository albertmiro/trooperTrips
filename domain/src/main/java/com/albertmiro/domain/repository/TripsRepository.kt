package com.albertmiro.domain.repository

import com.albertmiro.domain.models.Trip
import io.reactivex.Single

interface TripsRepository {
    fun getTrips(foreRefresh: Boolean = false): Single<Result<List<Trip>>>
    fun getTripDetail(id: Int): Single<Result<Trip>>
}