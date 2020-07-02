package com.example.corona.geomob.data.Model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tweet(

	@field:SerializedName("entries")
	val entries: Entries? = null,

	@field:SerializedName("tweetUrl")
	val tweetUrl: String? = null,

	@field:SerializedName("replies")
	val replies: Int? = null,

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("retweets")
	val retweets: Int? = null,

	@field:SerializedName("tweetId")
	val tweetId: String? = null,

	@field:SerializedName("isRetweet")
	val isRetweet: Boolean? = null,

	@field:SerializedName("likes")
	val likes: Int? = null,

	@field:SerializedName("username")
	val username: String? = null
) : Parcelable

@Parcelize
data class VideosItem(

	@field:SerializedName("id")
	val id: String? = null
) : Parcelable

@Parcelize
data class Entries(

	@field:SerializedName("urls")
	val urls: List<String?>? = null,

	@field:SerializedName("hashtags")
	val hashtags: List<String?>? = null,

	@field:SerializedName("videos")
	val videos: List<VideosItem?>? = null,

	@field:SerializedName("photos")
	val photos: List<String?>? = null
) : Parcelable
