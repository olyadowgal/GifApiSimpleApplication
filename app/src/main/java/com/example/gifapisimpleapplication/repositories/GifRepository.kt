package com.example.gifapisimpleapplication.repositories

import com.example.gifapisimpleapplication.entities.GifInfo
import com.example.gifapisimpleapplication.network.ApiClient

class GifRepository(private val apiClient: ApiClient) {

    suspend fun fetchGifs(search: String? = null, limit: Int = 10, pos: Int = 0): List<GifInfo> {
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
                dims = gif.dims
            )
        }
    }
}