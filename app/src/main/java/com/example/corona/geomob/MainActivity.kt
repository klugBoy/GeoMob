package com.example.corona.geomob

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.corona.geomob.data.Domaines.Historique
import com.example.corona.geomob.data.Domaines.Pays
import com.example.corona.geomob.data.Repository.SqlLiteDateBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.time.LocalDate
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() , CoroutineScope {

    private lateinit var job: Job


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        job = Job()
        launch {
            val country1 = Pays("Algeria","fficially the People's Democratic Republic of Algeria, is a country in the Maghreb region of North Africa.",
                43600000,
                2381741,
                "https://github.com/hjnilsson/country-flags/blob/master/png100px/dz.png",
                "https://upload.wikimedia.org/wikipedia/commons/7/79/National_anthem_of_Algeria%2C_by_the_U.S._Navy_Band.oga")
            val country2 = Pays("Morocco",
                "officially the Kingdom of Morocco.it's country located in the Maghreb region of North Africa.",
                710850,
                36472000,
                "https://github.com/hjnilsson/country-flags/blob/master/png100px/ma.png",
                "https://upload.wikimedia.org/wikipedia/commons/3/3f/National_Anthem_of_Morocco.ogg"
                )
            val country3 = Pays("Tunisia",
                "officially the Republic of Tunisia, is a country in the Maghreb region of North Africa",
                163610,
                11722038,
                "https://github.com/hjnilsson/country-flags/blob/master/png100px/tn.png",
                "https://upload.wikimedia.org/wikipedia/commons/2/23/Humat_al-Hima.ogg"
            )
            val country4 = Pays("Saudi Arabia",
            "officially the Kingdom of Saudi Arabia, is a country in Western Asia constituting the bulk of the Arabian Peninsula",
            2149690,
            34218169,
            "https://github.com/hjnilsson/country-flags/blob/master/png100px/sa.png",
            "https://upload.wikimedia.org/wikipedia/commons/6/6d/Saudi_Arabian_national_anthem%2C_performed_by_the_United_States_Navy_Band.oga"
            )
            val country5 = Pays("United Arab Emirates",
                "Sometimes simply called the Emirates,  is a country in Western Asia at the northeast end of the Arabian Peninsula on the Persian Gulf",
                83600,
                9890400,
                "https://github.com/hjnilsson/country-flags/blob/master/png100px/ae.png",
                "https://upload.wikimedia.org/wikipedia/commons/3/3e/%22Ishy_Bilady%22_performed_by_the_United_States_Navy_Band.oga")
            applicationContext?.let {
 //               val pays =  SqlLiteDateBase(it).getPaysDao().addMultiplePays(country1,country2,country3,country4,country5)
//                val paysSize = SqlLiteDateBase(it).getPaysDao().getAllPays().size
  //              val help = SqlLiteDateBase(it).getPaysDao().getPays(5)!!.nom

 //                 val historique = Historique("1830-07-05","Algeria was colonized by the French",1)
                  val historiqueId = SqlLiteDateBase(it).getHistoriqueDao().findByPaysId(1)
               // val help2 = SqlLiteDateBase(it).getHistoriqueDao().getHistorique(1)!!.dateHistorique
                    val k = historiqueId!!.get(0).dateHistorique
                Toast.makeText(applicationContext, "$k", Toast.LENGTH_LONG).show()

            }
        }
    }


    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }


}