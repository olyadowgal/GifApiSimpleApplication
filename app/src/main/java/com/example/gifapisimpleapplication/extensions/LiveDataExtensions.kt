package com.example.gifapisimpleapplication.extensions

import androidx.lifecycle.MutableLiveData

fun MutableLiveData<*>.notifyDataChanged() {
    value = value
}