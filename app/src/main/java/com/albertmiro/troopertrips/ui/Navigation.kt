package com.albertmiro.troopertrips.ui

import com.albertmiro.troopertrips.R
import com.albertmiro.troopertrips.ui.tripDetail.TripsDetailFragment
import com.albertmiro.troopertrips.ui.trips.TripsListFragment

fun MainActivity.loadTripsListFragment() {
    supportFragmentManager.beginTransaction()
        .replace(R.id.fragmentContainer, TripsListFragment.newInstance())
        .commit()
}

fun MainActivity.loadTripDetailsFragment(tripId : Long) {
    val tripsDetailFragment = TripsDetailFragment.newInstance(tripId)
    supportFragmentManager.beginTransaction()
        .addToBackStack(tripsDetailFragment.javaClass.name)
        .replace(R.id.fragmentContainer, tripsDetailFragment)
        .commit()
}
