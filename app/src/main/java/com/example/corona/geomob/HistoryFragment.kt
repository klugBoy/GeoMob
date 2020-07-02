package com.example.corona.geomob

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Visibility
import com.example.corona.geomob.data.Domaines.Historique
import com.example.corona.geomob.data.Domaines.Pays
import com.example.corona.geomob.data.Repository.SqlLiteDateBase
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.fragment_item_list.*
import kotlinx.android.synthetic.main.history_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class HistoryFragment : Fragment(),CellClickListener {
    lateinit var historyRecycleAdapter: HistoryRecycleAdapter
    var data = ArrayList<Historique>()
    var countryID : Int? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countryID = arguments?.getString("country_id")!!.toInt()
        CoroutineScope(Dispatchers.IO).launch{
            data = SqlLiteDateBase.getInstance(context)?.getHistoriqueDao()?.findByPaysId(countryID!!) as ArrayList<Historique>
            withContext(Dispatchers.Main){
                try {

                initRecycleView()
                historyRecycleAdapter.submitList(data)

                }  catch (e:Exception){}
            }
        }
    }
    private fun initRecycleView(){
        historyList.apply {
            layoutManager = LinearLayoutManager(context)
            val topSpacingItemDecoration = TopSpacingItemDecoration(5)
            addItemDecoration(topSpacingItemDecoration)
            historyRecycleAdapter = HistoryRecycleAdapter(this@HistoryFragment)
            adapter = historyRecycleAdapter

        }
    }

    override fun onCellClickListener(it: View) {
        val visibility = it.historyDescription.visibility
        if(visibility == View.VISIBLE) {
            it.historyLine.visibility = View.GONE
            it.historyDescription.visibility = View.GONE
        } else {
            it.historyLine.visibility = View.VISIBLE
            it.historyDescription.visibility = View.VISIBLE
        }
    }
}
