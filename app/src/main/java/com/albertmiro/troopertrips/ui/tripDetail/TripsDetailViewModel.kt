package com.albertmiro.troopertrips.ui.tripDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.albertmiro.domain.models.Trip
import com.albertmiro.domain.usecases.GetTripDetails
import com.albertmiro.troopertrips.ui.base.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException

class TripsDetailViewModel(val getTripDetails: GetTripDetails) :
    BaseViewModel(),
    TripsDetail.ViewModel {

    var isDataLoading: MutableLiveData<Boolean> = MutableLiveData()
    var isNetworkError: MutableLiveData<Boolean> = MutableLiveData()
    var isUnknownError: MutableLiveData<Boolean> = MutableLiveData()
    var trip: MutableLiveData<Trip> = MutableLiveData()

    override fun isDataLoading(): LiveData<Boolean> = isDataLoading

    override fun isNetworkError(): LiveData<Boolean> = isNetworkError

    override fun isUnknownError(): LiveData<Boolean> = isUnknownError

    override fun getTrip(): LiveData<Trip> = trip

    override fun getTripDetails(id: Long) {
        compositeDisposable.add(getTripDetails.invoke(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isDataLoading.setValue(true) }
            .subscribe(
                { result -> onSuccess(result) },
                { error -> onError(error) }
            ))
    }

    override fun onSuccess(result: Trip) {
        trip.postValue(result)
        isDataLoading.postValue(false)
        isNetworkError.postValue(false)
        isUnknownError.postValue(false)
    }

    override fun onError(error: Throwable?) {
        isDataLoading.postValue(false)
        when (error) {
            is UnknownHostException -> isNetworkError.postValue(true)
            else -> isUnknownError.postValue(true)
        }
    }
}
