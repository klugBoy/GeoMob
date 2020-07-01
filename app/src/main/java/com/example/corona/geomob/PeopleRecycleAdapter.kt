package com.example.corona.geomob

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.corona.geomob.data.Domaines.Pays
import com.example.corona.geomob.data.Domaines.Personnalite
import kotlinx.android.synthetic.main.fragment_item.view.*
import kotlinx.android.synthetic.main.person_item.view.*

class PeopleRecycleAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = ArrayList<Personnalite>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.person_item, parent, false)
        return PersonViewHolder(view)
    }

    fun submitList(countryList : ArrayList<Personnalite>){
        items = countryList
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int){
        when(holder){
            is PersonViewHolder -> {
                holder.bind(items[position])

            }}
    }

    override fun getItemCount(): Int = items.size

    inner class PersonViewHolder(view: View) : RecyclerView.ViewHolder(view){

        var personName : TextView? = view.namePerson
        var personPosition : TextView? = view.positionPerson
        fun bind(person : Personnalite){
            personName?.text = person.prenom + " "+ person.nom
            personPosition?.text = person.position
        }

    }
}