package com.example.gifapisimpleapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gifapisimpleapplication.db.dao.GifInfoDao
import com.example.gifapisimpleapplication.entities.GifInfo

@Database(
    entities = [GifInfo::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun gifInfoDao(): GifInfoDao
}