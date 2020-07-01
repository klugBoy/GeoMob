package com.example.corona.geomob

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_bottom_navigation_bar.*


class BottomNavigationBarFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_navigation_bar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottom_navigation.setOnNavigationItemSelectedListener  { item ->
            when(item.itemId) {
               R.id.history_page->{
                   (activity as HistoryPageInterface).historicPage()
               }
                R.id.info_page->{
                    (activity as AboutPageInterface).aboutPage()
                }
                R.id.images_page->{
                    (activity as ImagesPageInterface).imagesPage()
                }
                else->{
                    Toast.makeText(context,"Clicked",Toast.LENGTH_LONG).show()
                }
            }

            return@setOnNavigationItemSelectedListener true
        }
    }

}