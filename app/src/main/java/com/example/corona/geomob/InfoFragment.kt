package com.example.corona.geomob

import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.corona.geomob.data.Domaines.Pays
import com.example.corona.geomob.data.Domaines.Personnalite
import com.example.corona.geomob.data.Domaines.Ressource
import com.example.corona.geomob.data.Repository.SqlLiteDateBase
import kotlinx.android.synthetic.main.fragment_info.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.DecimalFormat


class InfoFragment : Fragment() {
    lateinit var personsRecyclerViewAdapter: PeopleRecycleAdapter
    lateinit var resourceRecyclerViewAdapter: ResourceRecycleAdapter
    var peopleData = ArrayList<Personnalite>()
    var resourceData = ArrayList<Ressource>()
    private lateinit var player : MediaPlayer
    var countryID = 0
    var audioPlaying = false
    lateinit var dataBase: SqlLiteDateBase
    lateinit var country : Pays

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        countryID = arguments?.getString("country_id")!!.toInt()
        dataBase = SqlLiteDateBase.getInstance(context)!!
        CoroutineScope(Dispatchers.IO).launch{
            country = dataBase.getPaysDao().getPaysById(countryID)
            withContext(Main){
                countryNameCard.text = country.nom
                countryFlagCard.setImageResource(country.urlDrapeau.toInt())
                setAudioPlayer(country.urlHymneNational)
                countryDescriptionCard.text = country.description
                countryAreaCard.text = DecimalFormat("#,###,###,###").format(country.surface) + " km2"
                countryPopulationCard.text = DecimalFormat("#,###,###,###").format(country.population)
            }
            peopleData = SqlLiteDateBase.getInstance(context)?.getPersonnaliteDAO()?.findByPaysId(countryID) as ArrayList<Personnalite>
            withContext(Main){
                try {

                initPeopleRecycleView()
                personsRecyclerViewAdapter.submitList(peopleData)
                }catch (e:Exception){}
            }
            resourceData = SqlLiteDateBase.getInstance(context)?.getRessourceDAO()?.findByPaysId(countryID) as ArrayList<Ressource>
            withContext(Main){
                try {
                initResourceRecycleView()
                resourceRecyclerViewAdapter.submitList(resourceData)
                }catch (e:Exception){}
            }
            }
        }





    private fun setAudioPlayer(src:String){
        val afd: AssetFileDescriptor? = context?.assets?.openFd(src)
        player = MediaPlayer()
        player.setDataSource(afd!!.fileDescriptor,afd!!.startOffset,afd!!.length)
        player.prepare()
        playAudio.setOnClickListener {
            audioPlaying = if(audioPlaying){
                playAudio.setImageResource(R.drawable.ic_play_audio)
                player.stop()
                player.release()
                player = MediaPlayer()
                player.setDataSource(afd!!.fileDescriptor,afd!!.startOffset,afd!!.length)
                player.prepare()
                false
            } else {
                playAudio.setImageResource(R.drawable.ic_stop_audio)
                player.start()
                true
            }
        }
    }


    override fun onPause() {
        super.onPause()
        try {
            playAudio.setImageResource(R.drawable.ic_play_audio)
            player.stop()
        }catch (e:Exception){
            Log.d("error","")
        }

    }



    private fun initPeopleRecycleView(){
        peopleRecycle.apply {
            layoutManager = LinearLayoutManager(context)
            val topSpacingItemDecoration = TopSpacingItemDecoration(2)
            addItemDecoration(topSpacingItemDecoration)
            personsRecyclerViewAdapter = PeopleRecycleAdapter()
            adapter = personsRecyclerViewAdapter

        }
    }
    private fun initResourceRecycleView(){
        resourceRecycle.apply {
            layoutManager = LinearLayoutManager(context)
            val topSpacingItemDecoration = TopSpacingItemDecoration(2)
            addItemDecoration(topSpacingItemDecoration)
            resourceRecyclerViewAdapter = ResourceRecycleAdapter()
            adapter = resourceRecyclerViewAdapter

        }
    }



}