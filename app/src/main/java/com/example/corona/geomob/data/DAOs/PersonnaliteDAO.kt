package com.example.corona.geomob.data.DAOs

import androidx.room.*
import com.example.corona.geomob.data.Domaines.Pays
import com.example.corona.geomob.data.Domaines.Personnalite
import com.example.corona.geomob.data.Domaines.Ressource


@Dao
interface PersonnaliteDAO {

    @Insert
    suspend fun addPersonnalite(personnalite: Personnalite)

    @Query("SELECT * FROM personnalite")
    suspend fun getAllPersonnalite() : List<Personnalite>

    @Query("SELECT * FROM personnalite WHERE pays_id = :pays_id")
    suspend fun findByPaysId(pays_id: Int) : List<Personnalite>

    @Query("SELECT * FROM personnalite WHERE id = :id")
    suspend fun getPersonnalite(id:Int) : Personnalite

    @Insert
    suspend fun addMultiplePersonnalite(vararg personnalite: Personnalite)

    @Update
    suspend fun updatePersonnalite(personnalite: Personnalite)

    @Delete
    suspend fun deletePersonnalite(personnalite: Personnalite)


}