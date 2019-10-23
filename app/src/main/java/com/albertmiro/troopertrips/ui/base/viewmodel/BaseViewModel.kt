package com.albertmiro.troopertrips.ui.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.albertmiro.domain.models.Trip
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel(), Base.ViewModel {

    val compositeDisposable = CompositeDisposable()

    override fun onNetworkError(exception: Throwable) {}

    override fun onUnknownError(exception: Throwable) {}

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    open fun getTrip(): LiveData<Trip> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}