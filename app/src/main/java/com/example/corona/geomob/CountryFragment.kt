package com.example.corona.geomob

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.corona.geomob.data.Domaines.Pays
import com.example.corona.geomob.data.Repository.SqlLiteDateBase
import kotlinx.android.synthetic.main.fragment_item_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class CountryFragment : Fragment(),CellClickListener  {
    lateinit var countryRecyclerViewAdapter: MyCountryRecyclerViewAdapter
    var data = ArrayList<Pays>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(IO).launch{
            data = SqlLiteDateBase.getInstance(context)?.getPaysDao()?.getAllPays() as ArrayList<Pays>
            withContext(Main){
                try {


                    initRecycleView()
                    Log.d("stringdata", data.toString())
                    countryRecyclerViewAdapter.submitList(data)
                }catch (e:Exception){}
            }
        }

    }


    private fun initRecycleView(){


        list.apply {
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL, false)
            val topSpacingItemDecoration = TopSpacingItemDecoration(5)
            addItemDecoration(topSpacingItemDecoration)
            countryRecyclerViewAdapter = MyCountryRecyclerViewAdapter(this@CountryFragment)
            adapter = countryRecyclerViewAdapter

    }
    }

    override fun onCellClickListener(it: View) {
        (activity as CountryDetailsActivityInterface).DetailsPage(it.tag.toString())
    }
}
