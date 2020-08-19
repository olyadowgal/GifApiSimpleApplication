package com.example.gifapisimpleapplication.viewmodels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.gifapisimpleapplication.R
import com.example.gifapisimpleapplication.activities.FullScreenActivity
import com.example.gifapisimpleapplication.adapters.FeedItemsAdapter
import com.example.gifapisimpleapplication.datasource.GifsDataSource
import com.example.gifapisimpleapplication.entities.GifInfo
import com.example.gifapisimpleapplication.livedata.ViewAction
import com.example.gifapisimpleapplication.repositories.GifRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FeedViewModel(
    application: Application,
    private val gifRepository: GifRepository
) : BaseViewModel(
    application
), FeedItemsAdapter.Callback {

    companion object {
        private val TAG: String = FeedViewModel::class.java.simpleName
    }

    private var query: String? = null
        set(value) {
            field = value
            dataSourceFactory.invalidateLatestDataSource()
        }

    private val _showSpinner = MutableLiveData<Boolean>()
    val showSpinner: LiveData<Boolean> = _showSpinner

    private val dataSourceFactory = GifsDataSource.Factory(
        gifRepository = gifRepository,
        getQuery = { query },
        onError = {
            GlobalScope.launch(Dispatchers.Main) {
                Toast.makeText(
                    application.baseContext,
                    application.getString(R.string.no_internet_error_text),
                    Toast.LENGTH_LONG
                ).show()
            }
        },
        onLoadingChanged = { _showSpinner.postValue(it) }
    )

    val data: LiveData<PagedList<GifInfo>> = LivePagedListBuilder(
        dataSourceFactory,
        PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .build()
    ).build()

    @MainThread
    fun onQueryChanged(query: String) {
        Log.d(TAG, query)
        this.query = query
        dataSourceFactory.invalidateLatestDataSource()
    }

    @MainThread
    fun onRefresh() {
        dataSourceFactory.invalidateLatestDataSource()
    }

    override fun onGifClick(gif: GifInfo) {
        _viewAction.value = ViewAction.Navigate(FullScreenActivity::class.java).putArg("gif", gif)
    }

    override fun onAddToFavoritesClick(gif: GifInfo) {
        GlobalScope.launch {
            if (gif.isFavorite) {
                gifRepository.insertToFavorites(gif)
            } else {
                gifRepository.deleteFromFavorites(gif)
            }
        }
    }
}