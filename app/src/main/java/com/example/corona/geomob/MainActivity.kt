package com.example.corona.geomob


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.list_frame,CountryFragment()).commit()
        supportFragmentManager.beginTransaction().replace(R.id.title_frame,TitleFragment()).commit()
    }




}