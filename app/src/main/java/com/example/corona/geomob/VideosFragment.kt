package com.example.corona.geomob

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import com.example.corona.geomob.data.Domaines.Image
import com.example.corona.geomob.data.Domaines.Video
import com.example.corona.geomob.data.Repository.SqlLiteDateBase
import kotlinx.android.synthetic.main.fragment_images.*
import kotlinx.android.synthetic.main.fragment_title_back.*
import kotlinx.android.synthetic.main.fragment_videos.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class VideosFragment : Fragment() {

    var countryID : Int? = null
    var data : ArrayList<Video>? = null
    var sizeData = 0
    var element : Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_videos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countryID = arguments?.getString("country_id")!!.toInt()
        CoroutineScope(Dispatchers.IO).launch{
            data = SqlLiteDateBase.getInstance(context)?.getVideoDAO()?.findByPaysId(countryID!!) as ArrayList<Video>?
            withContext(Dispatchers.Main){
                if (data!!.isNotEmpty()){
                    sizeData = data!!.size
                    displayVideo(element)
                    before_button.setOnClickListener {
                        if(element<=0) {element = sizeData-1}
                        else {element -= 1}
                        displayVideo(element)
                    }
                    next_button.setOnClickListener {
                        if((element+1)>= sizeData){element = 0}
                        else { element +=1 }
                        displayVideo(element)
                    }

                }
            }
        }
    }

    private fun displayVideo(element : Int){

        var video = data!![element]
        videoTitle.text = video.title
        videoFrame.setVideoURI(Uri.parse("android.resource://"+  context?.packageName + "/raw/"+video.urlVideo))

        // create an object of media controller
        val mediaController = MediaController(context)
        mediaController.setAnchorView(videoFrame)
        mediaController.setMediaPlayer(videoFrame)
        videoFrame.setMediaController(mediaController)

    }
}