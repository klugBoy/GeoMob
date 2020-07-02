package com.example.corona.geomob

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.RetryPolicy
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.corona.geomob.data.Model.Tweet
import com.example.corona.geomob.data.Repository.SqlLiteDateBase
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_social_media.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject


class SocialMediaFragment : Fragment() {
    var countryID : Int? = null
    lateinit var tweetsRecycleAdapter: TweetsRecycleAdapter
    lateinit var mRequestQueue: RequestQueue
    var countryName : String? =null
    var urlTwitterScraper : String = "https://scrap-yt.herokuapp.com/api/twitter?name="
    var data : ArrayList<Tweet>? = ArrayList<Tweet>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_social_media, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        countryID = arguments?.getString("country_id")!!.toInt()
        mRequestQueue = Volley.newRequestQueue(context)

        CoroutineScope(Dispatchers.IO).launch{
            countryName = SqlLiteDateBase.getInstance(context)?.getPaysDao()?.getPaysById(countryID!!)?.nom
            countryName = countryName?.replace("\\s".toRegex(), "")
            urlTwitterScraper += "$countryName&fbclid=IwAR0hzpi4CC4COZV5muYSTiDvH5fGsf7nZuQMYAEMpsq6dMC_C_Cpcecldv8"

            withContext(Dispatchers.Main) {

            getTweets(urlTwitterScraper,object :VolleyCallBack{
                override fun onSuccess() {
                        initRecycleView()
                        tweetsRecycleAdapter.submitList(data!!)
                }
            })
            }


        }

    }

    private fun initRecycleView(){
        tweetsList.apply {
            layoutManager = LinearLayoutManager(context)
            tweetsRecycleAdapter = TweetsRecycleAdapter()
            adapter = tweetsRecycleAdapter

        }
    }
    private fun getTweets(getUrl: String,callback:VolleyCallBack?) {

        val mJsonObjectRequest =
        object : StringRequest(
            Method.GET, getUrl,
            Response.Listener { response ->

                var jsonArray: JSONArray = JSONArray(response)
                var gson: Gson = Gson()
                var jsonObject: JSONObject = JSONObject()
                var tweetTempList = ArrayList<Tweet>()

                for (i in 0 until jsonArray.length()) {
                    jsonObject = jsonArray.getJSONObject(i)
                    tweetTempList.add(gson.fromJson(jsonObject.toString(), Tweet::class.java))
                }
                data = tweetTempList

                callback?.onSuccess()

                //it : JSON Object
            }, Response.ErrorListener {
                it.printStackTrace()
            }) {}

        mJsonObjectRequest.retryPolicy = object : RetryPolicy {
            override fun getCurrentTimeout(): Int {
                return 50000
            }

            override fun getCurrentRetryCount(): Int {
                return 50000
            }

            @Throws(VolleyError::class)
            override fun retry(error: VolleyError) {
            }
        }
        mRequestQueue.add(mJsonObjectRequest)


    }
    interface VolleyCallBack {
        fun onSuccess()
    }




}