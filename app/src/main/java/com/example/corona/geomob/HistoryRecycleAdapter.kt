package com.example.corona.geomob

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.corona.geomob.data.Domaines.Historique
import kotlinx.android.synthetic.main.history_item.view.*


class HistoryRecycleAdapter(private val cellClickListener: CellClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = ArrayList<Historique>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_item, parent, false)
        return HistoryViewHolder(view)
    }

    fun submitList(historyList : ArrayList<Historique>){
        items = historyList
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int){
        when(holder){
            is HistoryViewHolder -> {
                holder.bind(items[position])
                holder.itemView.setOnClickListener {
                    cellClickListener.onCellClickListener(it)
                }

            }}
    }

    override fun getItemCount(): Int = items.size

    inner class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private var historyDate : TextView? = view.historyDate
        private var historyDescription : TextView? = view.historyDescription
        var historicName : TextView? = view.historyName
        fun bind(historique : Historique){
            historyDate?.text = historique.dateHistorique
            historyDescription?.text = historique.description
            historicName?.text = historique.name
        }

    }
}