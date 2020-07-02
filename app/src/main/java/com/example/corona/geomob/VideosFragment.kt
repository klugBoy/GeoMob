package com.example.corona.geomob

import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.MediaController
import androidx.fragment.app.Fragment
import com.example.corona.geomob.data.Domaines.Video
import com.example.corona.geomob.data.Repository.SqlLiteDateBase
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
        adjustFramesLayouts()
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

    fun adjustFramesLayouts(){
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            videoTitle.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 2f
            )
            paneVideo.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 1f
            )
            paneButtons.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 2f
            )
        }else{
            videoTitle.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 3f
            )
            paneVideo.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 1f
            )
            paneButtons.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 3f
            )
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