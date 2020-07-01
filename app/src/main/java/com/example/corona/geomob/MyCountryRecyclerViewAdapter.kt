package com.example.corona.geomob

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.corona.geomob.data.Domaines.Pays
import kotlinx.android.synthetic.main.fragment_item.view.*

class MyCountryRecyclerViewAdapter(private val cellClickListener: CellClickListener)  : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = ArrayList<Pays>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return CountryViewHolder(view)
    }

    fun submitList(countryList : ArrayList<Pays>){
        items = countryList
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int){
        when(holder){
            is CountryViewHolder -> {
                holder.bind(items[position])
                holder.itemView.setOnClickListener {
                    cellClickListener.onCellClickListener(it)
                }

            }}
    }

    override fun getItemCount(): Int = items.size

    inner class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view){

        var countryName : TextView? = view.countryName
        var countryFlag : ImageView? = view.countryFlag
        fun bind(country : Pays){
            countryName?.text = country.nom
            Glide
                .with(itemView.context)
                .load(country.urlDrapeau.toInt())
                .into(countryFlag!!)
            itemView.tag = country.id
        }

    }
}