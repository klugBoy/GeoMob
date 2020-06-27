package com.example.corona.geomob.data.DAOs

import androidx.room.*
import com.example.corona.geomob.data.Domaines.Tweet
import com.example.corona.geomob.data.Domaines.Video


@Dao
interface TweetDAO {

    @Insert
    suspend fun addTweet(tweet: Tweet)

    @Query("SELECT * FROM tweet ORDER BY id DESC")
    suspend fun getAllTweet() : List<Tweet>

    @Query("SELECT * FROM tweet WHERE pays_id = :pays_id")
    suspend fun findByPaysId(pays_id: Int) : List<Tweet>

    @Query("SELECT * FROM tweet WHERE id = :id")
    suspend fun getTweet(id:Int) : Tweet

    @Insert
    suspend fun addMultipleTweet(vararg tweet: Tweet)

    @Update
    suspend fun updateTweet(tweet: Tweet)

    @Delete
    suspend fun deleteTweet(tweet: Tweet)
}