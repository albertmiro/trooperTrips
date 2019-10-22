package com.albertmiro.troopertrips.ui.trips

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.albertmiro.domain.models.Trip
import com.albertmiro.troopertrips.R
import com.albertmiro.troopertrips.extensions.isVisible
import com.albertmiro.troopertrips.extensions.showMessage
import com.albertmiro.troopertrips.ui.base.BaseFragment
import com.albertmiro.troopertrips.ui.loadTripDetailsFragment
import com.albertmiro.troopertrips.ui.trips.adapter.TripsAdapter
import com.albertmiro.troopertrips.ui.viewmodel.TripsViewModel
import kotlinx.android.synthetic.main.fragment_trips_list.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class TripsListFragment : BaseFragment(), TripsList.View {

    override val layoutId: Int = R.layout.fragment_trips_list

    private val tripsViewModel: TripsViewModel by sharedViewModel()

    private lateinit var tripsAdapter: TripsAdapter

    companion object {
        fun newInstance(): TripsListFragment {
            return TripsListFragment()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        hideBackOnToolbar()
        initAdapter()
        initList()
        initSwipeRefresh()
        initObservers()

        tripsViewModel.loadTrips(false)
    }

    private fun hideBackOnToolbar() {
        mainActivity.setSupportActionBar(toolbar)
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun initAdapter() {
        tripsAdapter = TripsAdapter().apply {
            onClickAction = {
                tripsViewModel.setCurrentTripId(it.id)
                mainActivity.loadTripDetailsFragment()
            }
        }
    }

    private fun initList() {

        tripsList.apply {
            adapter = tripsAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL, false
            )
            val divider = ContextCompat.getDrawable(context, R.drawable.ic_center)
            divider?.let {
                val dividerItemDecoration = DividerItemDecoration(
                    context,
                    (layoutManager as LinearLayoutManager).orientation
                )
                dividerItemDecoration.setDrawable(divider)
                addItemDecoration(dividerItemDecoration)
            }
        }
    }

    private fun initSwipeRefresh() {
        swipeRefresh.setOnRefreshListener {
            tripsViewModel.loadTrips(true)
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
            hideRefreshIcon()
        }
    }

    override fun onNetworkError(isNetworkError: Boolean) {
        if (isNetworkError) {
            context?.showMessage(getString(R.string.lost_connection))
            hideRefreshIcon()
        }
    }

    override fun changeProgressBarVisibility(isDataLoaded: Boolean) {
        if (!swipeRefresh.isRefreshing) {
            progressBar.isVisible(isDataLoaded)
        }
    }

    override fun showTrips(trips: List<Trip>) {
        hideRefreshIcon()
        tripsAdapter.setItems(trips)
        if (trips.isEmpty()) {
            context?.showMessage(getString(R.string.no_trips))
        }
    }

    override fun hideRefreshIcon() {
        if (swipeRefresh.isRefreshing) {
            swipeRefresh.isRefreshing = false
        }
    }
}