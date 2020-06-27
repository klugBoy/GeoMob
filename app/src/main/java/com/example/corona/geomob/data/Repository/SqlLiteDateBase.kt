package com.example.corona.geomob.data.Repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.corona.geomob.data.DAOs.HistoriqueDAO
import com.example.corona.geomob.data.DAOs.PaysDAO
import com.example.corona.geomob.data.Domaines.Historique
import com.example.corona.geomob.data.Domaines.Image
import com.example.corona.geomob.data.Domaines.Pays
import com.example.corona.geomob.data.Domaines.Personnalite
import java.util.concurrent.locks.Lock


@Database(entities = [(Pays::class),
    (Historique::class),
    (Personnalite::class),
    (Image::class)], version = 5)
abstract class SqlLiteDateBase:RoomDatabase() {

    abstract fun getPaysDao() :PaysDAO
    abstract fun getHistoriqueDao() :HistoriqueDAO

    companion object {

        @Volatile private  var instance : SqlLiteDateBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {

            instance ?: buildDataBase(context).also {
                instance = it
            }
        }

        private fun buildDataBase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            SqlLiteDateBase::class.java,
            "geomob.db"
        ).build()

    }
}