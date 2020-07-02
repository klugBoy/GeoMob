package com.example.corona.geomob.data.DAOs

import androidx.room.*
import com.example.corona.geomob.data.Domaines.Pays

@Dao
interface PaysDAO {

    @Insert
    suspend fun addPays(pays: Pays)

    @Query("SELECT * FROM pays ORDER BY nom DESC")
    suspend fun getAllPays() : List<Pays>

    @Query("SELECT * FROM pays WHERE id = :id")
    suspend fun getPaysById(id:Int) : Pays

    @Query("SELECT * FROM pays WHERE nom = :nom")
    suspend fun getPaysByName(nom:String) : Pays

    @Query("SELECT id FROM pays WHERE nom = :nom")
    suspend fun getIdPays(nom:String) : Int

    @Query("SELECT * FROM pays WHERE visite = 0")
    suspend fun getAllNonVisitePays() : List<Pays>

    @Query("UPDATE pays SET visite = 1 WHERE id = :id")
    suspend fun updateVisitePays(id:Int)

    @Insert
    suspend fun addMultiplePays(vararg pays: Pays)

    @Update
    suspend fun updatePays(pays: Pays)

    @Delete
    suspend fun deletePays(pays: Pays)

}