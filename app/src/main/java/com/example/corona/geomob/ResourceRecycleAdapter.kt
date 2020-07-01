package com.example.corona.geomob

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.corona.geomob.data.Domaines.Ressource
import kotlinx.android.synthetic.main.person_item.view.*
import kotlinx.android.synthetic.main.ressource_item.view.*

class ResourceRecycleAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = ArrayList<Ressource>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ressource_item, parent, false)
        return ResourceViewHolder(view)
    }

    fun submitList(ressourceList : ArrayList<Ressource>){
        items = ressourceList
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int){
        when(holder){
            is ResourceViewHolder -> {
                holder.bind(items[position])

            }}
    }

    override fun getItemCount(): Int = items.size

    inner class ResourceViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private var resourceName : TextView? = view.nomRessource
        private var resourceQuantite : TextView? = view.quantiteRessource
        fun bind(resource : Ressource){
            resourceName?.text = resource.nom
            resourceQuantite?.text = resource.quantite.toString() + " " + resource.unite
        }

    }
}