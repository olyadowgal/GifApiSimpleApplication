package com.example.gifapisimpleapplication.cache

import android.app.Application
import android.os.Environment
import android.util.Log
import com.example.gifapisimpleapplication.entities.GifInfo
import com.example.gifapisimpleapplication.repositories.GifRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URL
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue

class GifsCacheManager(
    private val application: Application,
    gifRepository: GifRepository
) {

    companion object {
        private const val TAG: String = "GifsCacheManager"
    }

    private val externalFilesDir: File by lazy {
        File(application.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "gifs").also {
            if (!it.exists()) it.mkdirs()
        }
    }

    private var blockingQueue: BlockingQueue<GifInfo> = ArrayBlockingQueue(1000)

    init {
        gifRepository.observeAllFavorites().observeForever { gifs: List<GifInfo> ->
            Log.d(TAG, "uncachedGifs = $gifs")

            //Download gifs
            gifs.forEach { gif: GifInfo ->
                if (!blockingQueue.contains(gif) && !File(externalFilesDir, gif.fileName).exists()) {
                    blockingQueue.put(gif)
                }
            }

            //Remove gifs that are not in favorites anymore
            val ids = gifs.map { it.id }
            externalFilesDir.listFiles().orEmpty().forEach { file ->
                if (!ids.contains(file.name.replace(".gif", ""))) {
                    file.delete()
                }
            }
        }
        startDownloadJob()
    }

    private fun startDownloadJob() {
        Log.d(TAG, "startDownloadJob")
        GlobalScope.launch(Dispatchers.IO) {
            while (true) {
                val gifInfo = blockingQueue.take()
                download(gifInfo)
            }
        }
    }

    suspend fun download(gif: GifInfo) = withContext(Dispatchers.IO) {
        Log.d(TAG, "download($gif)")
        val file = File(externalFilesDir, gif.fileName)
        URL(gif.url).openStream().use { input ->
            file.outputStream().use { output ->
                val buffer = ByteArray(1024)
                var bytesRead: Int
                while (input.read(buffer, 0, buffer.size).also { bytesRead = it } >= 0) {
                    output.write(buffer, 0, bytesRead)
                }
            }
        }
        Log.d(TAG, "download($gif): finished")
    }
}