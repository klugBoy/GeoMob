package com.example.corona.geomob.data.DAOs

import androidx.room.*
import com.example.corona.geomob.data.Domaines.Historique

@Dao
interface HistoriqueDAO {

    @Insert
    suspend fun addHistorique(historique: Historique)

    @Query("SELECT * FROM historique ORDER BY id DESC")
    suspend fun getAllHistorique() : List<Historique>

    @Query("SELECT * FROM historique WHERE pays_id = :pays_id")
    suspend fun findByPaysId(pays_id: Int) : List<Historique>

    @Query("SELECT * FROM historique WHERE id = :id")
    suspend fun getHistorique(id:Int) : Historique

    @Insert
    suspend fun addMultipleHistorique(vararg historique: Historique)

    @Update
    suspend fun updateHistorique(historique: Historique)

    @Delete
    suspend fun deleteHistorique(historique: Historique)
}
