package com.example.corona.geomob.data.DAOs

import androidx.room.*
import com.example.corona.geomob.data.Domaines.Video


@Dao
interface VideoDAO {

    @Insert
    suspend fun addVideo(video: Video)

    @Query("SELECT * FROM video ORDER BY id DESC")
    suspend fun getAllVideo() : List<Video>

    @Query("SELECT * FROM video WHERE pays_id = :pays_id")
    suspend fun findByPaysId(pays_id: Int) : List<Video>

    @Query("SELECT * FROM video WHERE id = :id")
    suspend fun getVideo(id:Int) : Video

    @Insert
    suspend fun addMultipleVideo(vararg video: Video)

    @Update
    suspend fun updateVideo(video: Video)

    @Delete
    suspend fun deleteVideo(video: Video)
}
