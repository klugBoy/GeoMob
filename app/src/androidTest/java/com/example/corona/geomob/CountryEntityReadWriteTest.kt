package com.example.corona.geomob

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.corona.geomob.data.DAOs.PaysDAO
import com.example.corona.geomob.data.Domaines.Pays
import com.example.corona.geomob.data.Repository.SqlLiteDateBase
import junit.framework.Assert.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.Exception


@RunWith(AndroidJUnit4::class)
class CountryEntityReadWriteTest {

    private var paysDAO: PaysDAO? = null
    private var db: SqlLiteDateBase? = null

    @Before
    fun onCreateDB() {
        val context = InstrumentationRegistry.getTargetContext()
        db = Room.inMemoryDatabaseBuilder(context, SqlLiteDateBase::class.java).build()
        paysDAO = db!!.getPaysDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        db!!.close()
    }

    @Test
    @Throws(Exception::class)
    fun readAndWriteTests()
    {
//        val pays:Pays = TestUtil
//
//        // insert
//        val insertedID = paysDAO!!.addPays(pays)
//        assertNotNull(insertedID)

    }

}