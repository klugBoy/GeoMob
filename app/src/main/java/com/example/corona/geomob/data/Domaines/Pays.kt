package com.example.corona.geomob.data.Domaines

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "pays")
data class Pays(

    @ColumnInfo(name = "nom") val nom:String,

    @ColumnInfo(name = "description") val description: String,

    @ColumnInfo(name = "surface") val surface: Long,

    @ColumnInfo(name = "population") val population : Long,

    @ColumnInfo(name = "urlDrapeau") val urlDrapeau: String,

    @ColumnInfo(name = "urlHymneNational") val urlHymneNational:String

) :Serializable {

    @PrimaryKey(autoGenerate = true) var id : Int = 0

}