package com.example.gifapisimpleapplication.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.gifapisimpleapplication.livedata.SingleLiveEvent
import com.example.gifapisimpleapplication.livedata.ViewAction

abstract class BaseViewModel(
    application: Application
) : AndroidViewModel(application) {

    @Suppress("PropertyName")
    protected val _viewAction = SingleLiveEvent<ViewAction>()
    val viewAction: LiveData<ViewAction> get() = _viewAction

}