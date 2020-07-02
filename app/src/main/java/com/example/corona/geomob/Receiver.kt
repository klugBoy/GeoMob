package com.example.corona.geomob

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class Receiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            Log.d("receeeeiver","start")
            val i = Intent(context, ServiceBoot::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startService(i)
            Log.d("receeeeiver","end")
        }
    }

}