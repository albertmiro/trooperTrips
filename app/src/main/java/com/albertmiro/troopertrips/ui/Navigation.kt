package com.albertmiro.troopertrips.ui

import com.albertmiro.troopertrips.R
import com.albertmiro.troopertrips.ui.trips.TripsListFragment

fun MainActivity.loadTripsListFragment() {
    supportFragmentManager.beginTransaction()
        .replace(R.id.fragmentContainer, TripsListFragment.newInstance())
        .commit()
}

//fun MainActivity.loadTripDetailsFragment() {
//    val vehiclesMapFragment = VehiclesMapFragment.newInstance()
//    supportFragmentManager.beginTransaction()
//        .addToBackStack(vehiclesMapFragment.javaClass.name)
//        .replace(R.id.fragmentContainer, vehiclesMapFragment)
//        .commit()
//}
