package com.example.corona.geomob

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Gallery
import android.widget.ImageSwitcher
import android.widget.ImageView
import android.widget.ViewSwitcher
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.corona.geomob.data.Domaines.Historique
import com.example.corona.geomob.data.Domaines.Image
import com.example.corona.geomob.data.Repository.SqlLiteDateBase
import kotlinx.android.synthetic.main.fragment_images.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ImagesFragment : Fragment() {
    var countryID : Int? = null
    var data : ArrayList<Image>? = null
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_images, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countryID = arguments?.getString("country_id")!!.toInt()
        CoroutineScope(Dispatchers.IO).launch{
            data = SqlLiteDateBase.getInstance(context)?.getImageDao()?.findByPaysId(countryID!!) as ArrayList<Image>
            withContext(Dispatchers.Main){
                for(image in data!!){
                    flipperImages(image.urlImage)
                }
                slideShow.startFlipping()
            }
        }


    }

    private fun flipperImages(image:String){
        val imageView = ImageView(context)
        Log.d("filpper","images")
        Glide.with(context!!)
            .load(image)
            .centerCrop()
            .placeholder(R.drawable.loading_spinner)
            .into(imageView)
        slideShow.addView(imageView)
        slideShow.flipInterval = 4000
        slideShow.isAutoStart = true
        slideShow.setInAnimation(context,android.R.anim.slide_in_left)
        slideShow.setOutAnimation(context,android.R.anim.slide_out_right)
    }

}