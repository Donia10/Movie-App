package com.example.movieapp.Model.remote

import com.example.movieapp.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieService {
    fun getMovieService(): MovieApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(MovieApi::class.java)

    }
}