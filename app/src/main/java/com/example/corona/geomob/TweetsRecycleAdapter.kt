package com.example.corona.geomob

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.corona.geomob.data.Domaines.Ressource
import com.example.corona.geomob.data.Model.Tweet
import kotlinx.android.synthetic.main.ressource_item.view.*
import kotlinx.android.synthetic.main.tweet_item.view.*

class TweetsRecycleAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        var items = ArrayList<Tweet>()
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.tweet_item, parent, false)
            return TweetViewHolder(view)
        }

        fun submitList(ressourceList : ArrayList<Tweet>){
            items = ressourceList
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int){
            when(holder){
                is TweetViewHolder -> {
                    holder.bind(items[position])
                }}
        }

        override fun getItemCount(): Int = items.size

        inner class TweetViewHolder(view: View) : RecyclerView.ViewHolder(view){
            var tweetUser:TextView? = view.tweetUser
            var tweetDate:TextView? = view.tweetDate
            var tweetText:TextView? = view.tweetText
            var tweetReplies:TextView? = view.tweetReplies
            var tweetLikes:TextView? = view.tweetLikes
            var tweetRetweets: TextView? = view.tweetRetweets
            fun bind(tweet: Tweet){
                tweetUser?.text = tweet.username
                tweetDate?.text = tweet.time
                tweetText?.text = tweet.text
                tweetLikes?.text = tweet.likes.toString()
                tweetRetweets?.text = tweet.retweets.toString()
                tweetReplies?.text = tweet.replies.toString()

            }

        }
    }
