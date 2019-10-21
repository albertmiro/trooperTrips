package com.albertmiro.data.api

import com.albertmiro.data.entities.TripEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET(".")
    fun getTrips(): Single<List<TripEntity>>

    @GET(".")
    fun getTripDetails(@Query("id") tripId: Int): Single<TripEntity>

}