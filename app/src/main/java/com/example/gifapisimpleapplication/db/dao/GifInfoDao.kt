package com.example.gifapisimpleapplication.db.dao

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

    @Query("SELECT * FROM Favorites WHERE id IN (:id)")
    suspend fun selectWithId(id : String): List<GifInfo>

    @Query("DELETE FROM Favorites WHERE id = (:id)")
    suspend fun delete(id : String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gif : GifInfo)
}