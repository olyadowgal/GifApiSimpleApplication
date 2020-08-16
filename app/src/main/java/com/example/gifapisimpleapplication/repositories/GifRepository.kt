package com.example.gifapisimpleapplication.repositories

import android.util.Log
import com.example.gifapisimpleapplication.db.dao.GifInfoDao
import com.example.gifapisimpleapplication.entities.GifInfo
import com.example.gifapisimpleapplication.network.ApiClient

class GifRepository(private val apiClient: ApiClient, private val gifInfoDao: GifInfoDao) {

    companion object {
        private const val TAG: String = "GifRepository"
    }

    suspend fun fetchGifs(search: String? = null, limit: Int = 10, pos: Int = 0): List<GifInfo> {
        Log.d(TAG, "fetchGift(seach = $search, limit = $limit, pos = $pos)")
        return apiClient.gifService.getSearch(
            search = search,
            limit = limit,
            pos = pos
        ).results.map {
            val gif = it.media.first().gif
            GifInfo(
                id = it.id,
                title = it.title,
                url = gif.url,
                preview = gif.preview,
                isFavorite = false
            )
        }
    }

    suspend fun insertToFavorites(gif : GifInfo) {
        gifInfoDao.insert(gif)
    }

    suspend fun deleteFromFavorites(gif : GifInfo) {
        gifInfoDao.delete(gif.id)
    }
}