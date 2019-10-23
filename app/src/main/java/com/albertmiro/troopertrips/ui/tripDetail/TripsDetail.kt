package com.albertmiro.troopertrips.ui.tripDetail

import androidx.lifecycle.LiveData
import com.albertmiro.domain.models.Trip
import com.albertmiro.troopertrips.ui.base.viewmodel.Base

interface TripsDetail {
    interface View : Base.View {
        fun showTrip(trip: Trip)
        fun changeProgressBarVisibility(isVisible: Boolean)
        fun onUnknownError(isUnknownError: Boolean)
        fun onNetworkError(isNetworkError: Boolean)
    }

    interface ViewModel {
        fun isDataLoading(): LiveData<Boolean>
        fun isNetworkError(): LiveData<Boolean>
        fun isUnknownError(): LiveData<Boolean>
        fun getTrip(): LiveData<Trip>
        fun getTripDetails(id: Long)
        fun onError(error: Throwable?)
        fun onSuccess(result: Trip)
    }
}