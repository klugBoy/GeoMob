package com.example.corona.geomob

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class CountryDetailActivity : AppCompatActivity()
    ,HistoryPageInterface,ImagesPageInterface,AboutPageInterface,
    VideosPageInterface,SocialMediaPageInterface {
    var countryID : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)


        supportFragmentManager.beginTransaction().replace(R.id.top_frame,TitleBackFragment()).commit()
        supportFragmentManager.beginTransaction().replace(R.id.bottom_frame,BottomNavigationBarFragment()).commit()

        countryID = intent.getStringExtra("country_id")
        aboutPage()
    }

    override fun historicPage() {
        val bundle = Bundle()
        bundle.putString("country_id",countryID)
        val fragment = HistoryFragment()
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.main_frame,fragment).commit()
    }

    override fun aboutPage() {
        val bundle = Bundle()
        bundle.putString("country_id",countryID )
        val fragment = InfoFragment()
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.main_frame,fragment).commit()
    }

    override fun videosPage() {
        val bundle = Bundle()
        bundle.putString("country_id",countryID )
        val fragment = VideosFragment()
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.main_frame,fragment).commit()
    }

    override fun imagesPage() {
        val bundle = Bundle()
        bundle.putString("country_id",countryID )
        val fragment = ImagesFragment()
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.main_frame,fragment).commit()
    }

    override fun socialMediaPage() {
        val bundle = Bundle()
        bundle.putString("country_id",countryID )
        val fragment = SocialMediaFragment()
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.main_frame,fragment).commit()
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

}