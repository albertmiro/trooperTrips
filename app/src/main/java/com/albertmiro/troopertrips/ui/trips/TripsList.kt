package com.albertmiro.troopertrips.ui.trips

import androidx.lifecycle.LiveData
import com.albertmiro.domain.models.Trip
import com.albertmiro.troopertrips.ui.base.viewmodel.Base

interface TripsList {
    interface View : Base.View {
        fun showTrips(trips: List<Trip>)
        fun hideRefreshIcon()
        fun changeProgressBarVisibility(isVisible: Boolean)
        fun onUnknownError(isUnknownError: Boolean)
        fun onNetworkError(isNetworkError: Boolean)
    }

    interface ViewModel {
        fun isDataLoading(): LiveData<Boolean>
        fun isNetworkError(): LiveData<Boolean>
        fun isUnknownError(): LiveData<Boolean>
        fun getTrips(): LiveData<List<Trip>>
        fun loadTrips(forceRefresh: Boolean)
        fun onError(error: Throwable?)
        fun onSuccess(result: List<Trip>?)
    }
}