package com.albertmiro.troopertrips.ui.trips

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.albertmiro.domain.models.Trip
import com.albertmiro.domain.usecases.GetTrips
import com.albertmiro.troopertrips.ui.base.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException

class TripsViewModel(val getTrips: GetTrips) : BaseViewModel(),
    TripsList.ViewModel {

    var isDataLoading: MutableLiveData<Boolean> = MutableLiveData()
    var isNetworkError: MutableLiveData<Boolean> = MutableLiveData()
    var isUnknownError: MutableLiveData<Boolean> = MutableLiveData()
    var trips: MutableLiveData<List<Trip>> = MutableLiveData()

    override fun isDataLoading(): LiveData<Boolean> = isDataLoading

    override fun isNetworkError(): LiveData<Boolean> = isNetworkError

    override fun isUnknownError(): LiveData<Boolean> = isUnknownError

    override fun getTrips(): LiveData<List<Trip>> = trips

    override fun loadTrips(forceRefresh: Boolean) {
        compositeDisposable.add(getTrips.invoke(forceRefresh)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isDataLoading.setValue(true) }
            .subscribe(
                { result -> onSuccess(result) },
                { error -> onError(error) }
            ))
    }

    override fun onSuccess(result: List<Trip>?) {
        trips.postValue(result)
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
