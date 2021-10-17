package com.example.movieapp.Model.remote

import com.example.movieapp.Model.Movie
import com.example.movieapp.Model.MoviesResponse
import com.example.movieapp.Model.VideoDataClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET(value = "search/movie?")
    suspend fun getMovies(
            @Query("api_key") apiKey:String = "32bdbd740a2356800eff3503e0f1217f",
            @Query(value = "query") query:String
                         ): Response<MoviesResponse>
    @GET("movie/videos?")
    suspend fun getVideo(
            @Query("api_key") apiKey:String = "32bdbd740a2356800eff3503e0f1217f",
            @Query(value = "id") query:Int
    ): Response<VideoDataClass>
}