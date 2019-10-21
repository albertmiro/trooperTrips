package com.albertmiro.data.repository

import com.albertmiro.data.mapper.TripsMapper
import com.albertmiro.domain.models.Trip
import com.albertmiro.domain.repository.Result
import com.albertmiro.domain.repository.TripsRepository
import io.reactivex.Single

class TripsRepositoryImpl(
    private val dataSource: APIDataSource,
    private val mapper: TripsMapper
) : TripsRepository {

    override fun getTrips(foreRefresh: Boolean): Single<Result<List<Trip>>> {
        return when (val trips = dataSource.getTrips()) {
            is Result.Success -> trips.value.map { Result.Success(mapper.toTripList(it)) }
            is Result.Failure -> Single.just(Result.Failure(trips.value))
        }
    }

    override fun getTripDetail(id: Int): Single<Result<Trip>> {
        return when (val trip = dataSource.getTripDetails(id)) {
            is Result.Success -> trip.value.map { Result.Success(mapper.toTrip(it)) }
            is Result.Failure -> Single.just(Result.Failure(trip.value))
        }
    }
}

