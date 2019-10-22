package com.albertmiro.troopertrips.ui.tripDetail

import android.os.Bundle
import androidx.lifecycle.Observer
import com.albertmiro.domain.models.Trip
import com.albertmiro.troopertrips.R
import com.albertmiro.troopertrips.extensions.isVisible
import com.albertmiro.troopertrips.extensions.showMessage
import com.albertmiro.troopertrips.ui.base.BaseFragment
import com.albertmiro.troopertrips.ui.viewmodel.TripsViewModel
import kotlinx.android.synthetic.main.fragment_trips_list.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TripsDetailFragment : BaseFragment(), TripsDetail.View {

    override val layoutId: Int = R.layout.fragment_trip_detail

    private val tripsViewModel: TripsViewModel by sharedViewModel()

    companion object {
        fun newInstance(): TripsDetailFragment {
            return TripsDetailFragment()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        showBackOnToolbar()
        initObservers()

        tripsViewModel.loadTrips(false)
    }

    private fun showBackOnToolbar() {
        with(mainActivity) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun initObservers() {
        tripsViewModel.isDataLoading()
            .observe(this, Observer { changeProgressBarVisibility(it) })
        tripsViewModel.getTrips().observe(this, Observer { showTrips(it) })
        tripsViewModel.isNetworkError().observe(this, Observer { onNetworkError(it) })
        tripsViewModel.isUnknownError().observe(this, Observer { onUnknownError(it) })
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

    override fun showTrips(trips: List<Trip>) {
        if (trips.isEmpty()) {
            context?.showMessage(getString(R.string.no_trips))
        }
    }
}