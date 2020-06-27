package com.example.corona.geomob.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "pays")
data class Pays(

    @ColumnInfo(name = "description") val title: String,

    @ColumnInfo(name = "surface") val body: String,

    @ColumnInfo(name = "population") val _date : String,

    @ColumnInfo(name = "urlDrapeau") val codecolor: Int,

    @ColumnInfo(name = "urlHymneNational") val 


) :Serializable {

    @PrimaryKey(autoGenerate = true) var id : Int = 0

}