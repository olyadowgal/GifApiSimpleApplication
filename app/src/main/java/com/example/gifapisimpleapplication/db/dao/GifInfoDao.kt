package com.example.gifapisimpleapplication.db.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gifapisimpleapplication.entities.GifInfo

@Dao
interface GifInfoDao {

    @Query("SELECT * FROM Favorites")
    fun selectAll(): DataSource.Factory<Int, GifInfo>

    @Query("SELECT * FROM Favorites")
    fun observerAll(): LiveData<List<GifInfo>>

    @Query("SELECT EXISTS(SELECT * FROM Favorites WHERE id = :id)")
    suspend fun existWithId(id : String): Boolean

    @Query("DELETE FROM Favorites WHERE id = :id")
    suspend fun delete(id : String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gif : GifInfo)
}