package com.example.gifapisimpleapplication.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.gifapisimpleapplication.adapters.FeedItemsAdapter
import com.example.gifapisimpleapplication.entities.GifInfo
import com.example.gifapisimpleapplication.repositories.GifRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FeedViewModel(application: Application, private val gifRepository: GifRepository) : BaseViewModel(application), FeedItemsAdapter.Callback {

    val feedItemsAdapter : FeedItemsAdapter = FeedItemsAdapter( this)

    fun init(isFavorite :Boolean) {
        if (!isFavorite) {
            viewModelScope.launch {
                feedItemsAdapter.setGifs(gifRepository.fetchGifs("cats",10,0))
            }
        }

    }

    override fun onClick(gif: GifInfo) {
        TODO("Not yet implemented")
    }


}