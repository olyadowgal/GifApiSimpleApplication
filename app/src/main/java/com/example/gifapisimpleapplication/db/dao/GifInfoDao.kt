package com.example.gifapisimpleapplication.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gifapisimpleapplication.entities.GifInfo

@Dao
interface GifInfoDao {

    @Query("SELECT * FROM Favorites")
    suspend fun selectAll(): List<GifInfo>

    @Query("SELECT * FROM Favorites WHERE id IN (:ids)")
    suspend fun selectAllWithId(ids : List<String>): List<GifInfo>

    @Query("DELETE FROM Favorites WHERE id = (:id)")
    suspend fun delete(id : String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gif : GifInfo)
}