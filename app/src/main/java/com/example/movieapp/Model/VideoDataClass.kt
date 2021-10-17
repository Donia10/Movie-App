package com.example.movieapp.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Video(@SerializedName("id")@Expose val id:Int,
                          @SerializedName("ket")@Expose val key:String,
                          @SerializedName("name")@Expose val name:String)
data class VideoDataClass(@SerializedName("id") @Expose val id: Int,
                           @SerializedName("results")@Expose val results:List<Video> )