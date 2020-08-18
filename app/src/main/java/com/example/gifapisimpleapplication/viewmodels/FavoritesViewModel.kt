package com.example.gifapisimpleapplication.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.gifapisimpleapplication.activities.FullScreenActivity
import com.example.gifapisimpleapplication.adapters.FeedItemsAdapter
import com.example.gifapisimpleapplication.entities.GifInfo
import com.example.gifapisimpleapplication.livedata.ViewAction
import com.example.gifapisimpleapplication.repositories.GifRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoritesViewModel(
    application: Application,
    private val gifRepository: GifRepository
) : BaseViewModel(
    application
), FeedItemsAdapter.Callback {

    val data: LiveData<PagedList<GifInfo>> = LivePagedListBuilder(
        gifRepository.getAllFavorites(),
        PagedList.Config.Builder()
            .setPageSize(10)
            .build()
    ).build()

    override fun onGifClick(gif: GifInfo) {
        _viewAction.value = ViewAction.Navigate(FullScreenActivity::class.java).putArg("gif",gif)
    }

    /**
     * We can only delete GIFs from favorites on this screen
     */
    override fun onAddToFavoritesClick(gif: GifInfo) {
        GlobalScope.launch {
            gifRepository.deleteFromFavorites(gif)
        }
    }


}