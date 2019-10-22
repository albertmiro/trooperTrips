package com.albertmiro.data.api

import com.albertmiro.data.entities.TripEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {

    @GET("trips")
    fun getTrips(): Single<List<TripEntity>>

    @GET("trips/{id}")
    fun getTripDetails(@Path("id") tripId: Long): Single<TripEntity>

}