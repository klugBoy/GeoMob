package com.example.corona.geomob


import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.corona.geomob.data.Repository.SqlLiteDateBase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity() ,CountryDetailsActivityInterface
    ,HistoryPageInterface,ImagesPageInterface,AboutPageInterface,
    VideosPageInterface,SocialMediaPageInterface{

    private var countryID : String? = null
    var bottomFragment : Fragment? = null
    var detaislFragment : Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        supportFragmentManager.beginTransaction().replace(R.id.list_frame,CountryFragment()).commit()
        supportFragmentManager.beginTransaction().replace(R.id.title_frame,TitleFragment()).commit()
        setFramesVisibility()
    }

    fun setFramesVisibility(){
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            frameDetails.visibility = View.VISIBLE
        }else{
            frameDetails.visibility = View.GONE
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            frameDetails.visibility = View.VISIBLE

        } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
            frameDetails.visibility = View.GONE
            try {
                supportFragmentManager.beginTransaction().remove(supportFragmentManager.findFragmentById(R.id.bottom_frame)!!).commit()
                supportFragmentManager.beginTransaction().remove(supportFragmentManager.findFragmentById(R.id.main_frame)!!).commit()
            }catch (e:Exception){}

        }
    }

    override fun DetailsPage(countryID: String) {
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            this.countryID = countryID
            supportFragmentManager.beginTransaction().replace(R.id.bottom_frame,BottomNavigationBarFragment()).commit()
            aboutPage()
        } else {
            val intent = Intent(this,CountryDetailActivity::class.java)
            intent.putExtra("country_id", countryID)
            startActivity(intent)
        }
        CoroutineScope(Dispatchers.IO).launch {
            SqlLiteDateBase.getInstance(applicationContext)!!.getPaysDao().updateVisitePays(countryID.toInt())
        }
    }

    override fun DetailsPageLandscape(countryID: String) {
        frameDetails.visibility = View.GONE
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

}