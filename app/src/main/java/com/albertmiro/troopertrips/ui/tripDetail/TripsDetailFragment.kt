package com.albertmiro.troopertrips.ui.tripDetail

import android.os.Bundle
import androidx.lifecycle.Observer
import com.albertmiro.domain.models.Trip
import com.albertmiro.troopertrips.R
import com.albertmiro.troopertrips.extensions.isVisible
import com.albertmiro.troopertrips.extensions.showMessage
import com.albertmiro.troopertrips.ui.BindTripUtils
import com.albertmiro.troopertrips.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_trip_detail.*
import kotlinx.android.synthetic.main.fragment_trips_list.progressBar
import kotlinx.android.synthetic.main.fragment_trips_list.toolbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class TripsDetailFragment(private val tripId: Long) : BaseFragment(), TripsDetail.View {

    override val layoutId: Int = R.layout.fragment_trip_detail

    private val tripDetailsViewModel: TripsDetailViewModel by viewModel()

    companion object {
        fun newInstance(id: Long): TripsDetailFragment {
            return TripsDetailFragment(id)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        showBackOnToolbar()
        initObservers()

        tripDetailsViewModel.getTripDetails(tripId)
    }

    private fun showBackOnToolbar() {
        with(mainActivity) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun initObservers() {
        tripDetailsViewModel.isDataLoading()
            .observe(this, Observer { changeProgressBarVisibility(it) })
        tripDetailsViewModel.getTrip().observe(this, Observer { showTrip(it) })
        tripDetailsViewModel.isNetworkError().observe(this, Observer { onNetworkError(it) })
        tripDetailsViewModel.isUnknownError().observe(this, Observer { onUnknownError(it) })
    }

    override fun onUnknownError(isUnknownError: Boolean) {
        if (isUnknownError) {
            context?.showMessage(getString(R.string.unexpected_error))
        }
    }

    override fun onNetworkError(isNetworkError: Boolean) {
        if (isNetworkError) {
            context?.showMessage(getString(R.string.lost_connection))
        }
    }

    override fun changeProgressBarVisibility(isDataLoaded: Boolean) {
        progressBar.isVisible(isDataLoaded)
    }

    override fun showTrip(trip: Trip) {
        BindTripUtils.bindDetails(
            trip,
            avatarImage,
            pilotName,
            pickUpPlanetImage,
            pickUpPlanetName,
            pickUpPlanetTime,
            dropOffPlanetImage,
            dropOffPlanetName,
            dropOffPlanetTime,
            tripDistance,
            tripDuration,
            ratingBar
        )
    }
}