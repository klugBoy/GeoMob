package com.example.corona.geomob

import android.view.View

interface CellClickListener {
    fun onCellClickListener(it: View)
}

interface AboutPageInterface {
    fun aboutPage()
}

interface VideosPageInterface {
    fun videosPage()
}
interface ImagesPageInterface {
    fun imagesPage()
}
interface HistoryPageInterface {
    fun historicPage()
}
interface SocialMediaPageInterface {
    fun socialMediaPage()
}

interface CountryDetailsActivityInterface {
    fun DetailsPage(countryID : String)
    fun DetailsPageLandscape(countryID : String)
}