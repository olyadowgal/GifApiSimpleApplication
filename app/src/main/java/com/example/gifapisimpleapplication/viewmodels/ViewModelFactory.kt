package com.example.gifapisimpleapplication.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.gifapisimpleapplication.cache.GifsCacheManager
import com.example.gifapisimpleapplication.repositories.GifRepository
import java.lang.RuntimeException

class ViewModelFactory(
    private val application: Application,
    private val gifRepository: GifRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return when (modelClass) {
            FeedViewModel::class.java -> {
                FeedViewModel(
                    application,
                    gifRepository
                ) as T
            }
            FavoritesViewModel::class.java -> {
                FavoritesViewModel(
                    application,
                    gifRepository
                ) as T
            }
            else -> throw RuntimeException()
        }
    }
}