package com.example.corona.geomob.data.DAOs

import androidx.room.*
import com.example.corona.geomob.data.Domaines.Personnalite
import com.example.corona.geomob.data.Domaines.Ressource


@Dao
interface RessourceDAO {
    @Insert
    suspend fun addRessource(ressource: Ressource)

    @Query("SELECT * FROM ressource")
    suspend fun getAllRessource() : List<Ressource>

    @Query("SELECT * FROM ressource WHERE id = :id")
    suspend fun getRessource(id:Int) : Ressource

    @Insert
    suspend fun addMultipleRessource(vararg ressource: Ressource)

    @Update
    suspend fun updateRessource(ressource: Ressource)

    @Delete
    suspend fun deleteRessourcee(ressource: Ressource)
}