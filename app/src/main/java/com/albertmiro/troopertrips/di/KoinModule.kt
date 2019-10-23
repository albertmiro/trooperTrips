package com.albertmiro.troopertrips.di

import com.albertmiro.troopertrips.ui.tripDetail.TripsDetailViewModel
import com.albertmiro.troopertrips.ui.trips.TripsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    viewModel { TripsViewModel(getTrips = get()) }
    viewModel { TripsDetailViewModel(getTripDetails = get()) }
}


