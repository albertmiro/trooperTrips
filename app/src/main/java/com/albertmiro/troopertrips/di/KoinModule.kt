package com.albertmiro.troopertrips.di

import com.albertmiro.troopertrips.ui.viewmodel.TripsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    viewModel { TripsViewModel(getTrips = get()) }
}


