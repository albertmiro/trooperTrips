package com.albertmiro.data.repository

import com.albertmiro.data.api.APIService
import com.albertmiro.data.entities.TripEntity
import com.albertmiro.domain.repository.Result
import io.reactivex.Single

class APIDataSource(private val service: APIService) {

    fun getTrips(): Result<Single<List<TripEntity>>> {
        return try {
            val trips = service.getTrips()
            Result.Success(trips)
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }

    fun getTripDetails(id: Int): Result<Single<TripEntity>> {
        return try {
            val tripDetail = service.getTripDetails(id)
            Result.Success(tripDetail)
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }
}


