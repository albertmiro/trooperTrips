package com.albertmiro.domain.di

import com.albertmiro.domain.usecases.GetTripDetails
import com.albertmiro.domain.usecases.GetTrips
import org.koin.core.module.Module
import org.koin.dsl.module

val useCaseModule: Module = module {
    factory { GetTrips(tripsRepository = get()) }
    factory { GetTripDetails(tripsRepository = get()) }
}