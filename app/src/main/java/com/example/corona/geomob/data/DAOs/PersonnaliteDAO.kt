package com.example.corona.geomob.data.DAOs

import androidx.room.*
import com.example.corona.geomob.data.Domaines.Pays
import com.example.corona.geomob.data.Domaines.Personnalite


@Dao
interface PersonnaliteDAO {

    @Insert
    suspend fun addPersonnalite(personnalite: Personnalite)

    @Query("SELECT * FROM personnalite")
    suspend fun getAllPersonnalite() : List<Personnalite>

    @Query("SELECT * FROM personnalite WHERE id = :id")
    suspend fun getPersonnalite(id:Int) : Personnalite

    @Insert
    suspend fun addMultiplePersonnalite(vararg personnalite: Personnalite)

    @Update
    suspend fun updatePersonnalite(personnalite: Personnalite)

    @Delete
    suspend fun deletePersonnalite(personnalite: Personnalite)


}