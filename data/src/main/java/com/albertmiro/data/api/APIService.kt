package com.albertmiro.data.api

import com.albertmiro.data.entities.TripEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("trips")
    fun getTrips(): Single<List<TripEntity>>

    @GET("trips")
    fun getTripDetails(@Query("id") tripId: Int): Single<TripEntity>

}