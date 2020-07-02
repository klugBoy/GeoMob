package com.example.corona.geomob

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.corona.geomob.data.Domaines.Historique
import com.example.corona.geomob.data.Domaines.Pays
import com.example.corona.geomob.data.Repository.SqlLiteDateBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


class ServiceBoot : Service(){
    var data : ArrayList<Pays>? = ArrayList<Pays>()
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("receeeeiver","service_start")

        CoroutineScope(Dispatchers.IO).launch{
            data = SqlLiteDateBase.getInstance(applicationContext)?.getPaysDao()?.getAllNonVisitePays() as ArrayList<Pays>
            withContext(Dispatchers.Main){
                if(data!!.isNotEmpty()){
                Log.d("receeeeiver","notification_start")
                var appIntent = Intent( baseContext,MainActivity::class.java)
                val notification = NotificationCompat.Builder(baseContext,"CHANNEL_ID")
                    .setSmallIcon(data!![0].urlDrapeau.toInt())
                    .setContentTitle(data!![0].nom)
                    .setContentText(data!![0].description)
                    .setAutoCancel(true)
                    .setContentIntent(
                PendingIntent.getActivity(baseContext, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT)
                )
                    .build()

                val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                manager.notify(1002,notification)
                Log.d("receeeeiver","notification_end")
                }
            }
        }
        stopSelf()
    }

}