package com.example.corona.geomob.data.Repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.corona.geomob.data.DAOs.*
import com.example.corona.geomob.data.Domaines.*
import java.util.concurrent.locks.Lock


@Database(entities = [
    (Pays::class),
    (Historique::class),
    (Personnalite::class),
    (Image::class),
    (Tweet::class),
    (Video::class),
    (Ressource::class)
], version = 5)
abstract class SqlLiteDateBase:RoomDatabase() {

    abstract fun getPaysDao() :PaysDAO
    abstract fun getHistoriqueDao() :HistoriqueDAO
    abstract fun getPersonnaliteDAO() :PersonnaliteDAO
    abstract fun getImageDao() :ImageDAO
    abstract fun getTweetDAO() :TweetDAO
    abstract fun getVideoDAO() :VideoDAO
    abstract fun getRessourceDAO() :RessourceDAO

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