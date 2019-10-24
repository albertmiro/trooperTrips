package com.albertmiro.troopertrips.ui.tripDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.albertmiro.domain.models.Trip
import com.albertmiro.domain.usecases.GetTripDetails
import com.albertmiro.troopertrips.ui.base.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException

class TripsDetailViewModel(val getTripDetails: GetTripDetails) : BaseViewModel() {

    private var isDataLoading: MutableLiveData<Boolean> = MutableLiveData()
    private var isNetworkError: MutableLiveData<Boolean> = MutableLiveData()
    private var isUnknownError: MutableLiveData<Boolean> = MutableLiveData()
    private var trip: MutableLiveData<Trip> = MutableLiveData()

    fun isDataLoading(): LiveData<Boolean> = isDataLoading

    fun isNetworkError(): LiveData<Boolean> = isNetworkError

    fun isUnknownError(): LiveData<Boolean> = isUnknownError

    fun getTrip(): LiveData<Trip> = trip

    fun getTripDetails(id: Long) {
        compositeDisposable.add(getTripDetails.invoke(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isDataLoading.setValue(true) }
            .subscribe(
                { result -> onSuccess(result) },
                { error -> onError(error) }
            ))
    }

    private fun onSuccess(result: Trip) {
        trip.postValue(result)
        isDataLoading.postValue(false)
        isNetworkError.postValue(false)
        isUnknownError.postValue(false)
    }

    private fun onError(error: Throwable?) {
        isDataLoading.postValue(false)
        when (error) {
            is UnknownHostException -> isNetworkError.postValue(true)
            else -> isUnknownError.postValue(true)
        }
    }
}
