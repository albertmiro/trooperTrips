package com.albertmiro.data.repository

import com.albertmiro.data.api.APIService
import com.albertmiro.data.entities.TripEntity
import io.reactivex.Single

class APIDataSource(private val service: APIService) {

    fun getTrips(): Pair<Single<List<TripEntity>>, Exception?> {
        return try {
            service.getTrips() to null
        } catch (e: Exception) {
            Single.just(emptyList<TripEntity>()) to e
        }
    }

    fun getTripDetails(id: Long): Pair<Single<TripEntity>, Exception?> {
        return try {
            service.getTripDetails(id) to null
        } catch (e: Exception) {
            Single.just(TripEntity.empty()) to e
        }
    }
}