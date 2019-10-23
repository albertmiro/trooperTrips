package com.albertmiro.data.repository

import com.albertmiro.data.mapper.TripsMapper
import com.albertmiro.domain.models.Trip
import com.albertmiro.domain.repository.TripsRepository
import io.reactivex.Single

class TripsRepositoryImpl(
    private val dataSource: APIDataSource,
    private val mapper: TripsMapper
) : TripsRepository {

    override fun getTrips(foreRefresh: Boolean): Single<List<Trip>> {
        val trips = dataSource.getTrips()
        return trips.first.map { mapper.toTripList(it) }
    }

    override fun getTripDetail(id: Long): Single<Trip> {
        val trip = dataSource.getTripDetails(id)
        return trip.first.map { mapper.toTrip(it) }
    }
}

