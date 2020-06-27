package com.example.corona.geomob.data.DAOs

import androidx.room.*
import com.example.corona.geomob.data.Domaines.Historique
import com.example.corona.geomob.data.Domaines.Image


@Dao
interface ImageDAO {

    @Insert
    suspend fun addImage(image: Image)

    @Query("SELECT * FROM image ORDER BY id DESC")
    suspend fun getAllImage() : List<Image>

    @Query("SELECT * FROM image WHERE pays_id = :pays_id")
    suspend fun findByPaysId(pays_id: Int) : List<Image>

    @Query("SELECT * FROM image WHERE id = :id")
    suspend fun getImage(id:Int) : Image

    @Insert
    suspend fun addMultipleImage(vararg image: Image)

    @Update
    suspend fun updateImage(image: Image)

    @Delete
    suspend fun deleteImage(image: Image)

}