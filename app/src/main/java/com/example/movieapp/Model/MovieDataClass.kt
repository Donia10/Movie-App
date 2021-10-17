package com.example.movieapp.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MoviesResponse(@SerializedName (value = "page") @Expose var page:Int? ,
                          @SerializedName (value = "results") @Expose var results:List<Movie>?,
                          @SerializedName (value = "total_pages") @Expose var totalPages:Int?,
                          @SerializedName(value = "total_results") @Expose var total_results:Int?
)
data class Movie ( @SerializedName (value = "id")@Expose var id: Int ,
                   @SerializedName(value = "backdrop_path") @Expose var backdropPath:String? ,
                   @SerializedName(value = "title") @Expose var title:String? ,
                   @SerializedName(value = "vote_average") @Expose var voteAverage:String? ,
                   @SerializedName(value = "overview") @Expose var overview:String? ,
                   @SerializedName(value = "poster_path") @Expose var posterpath:String? ,
                   @SerializedName(value = "release_date") @Expose var releaseDate:String?,
                   @SerializedName(value = "video") @Expose var video:Boolean?
                    ):Serializable

