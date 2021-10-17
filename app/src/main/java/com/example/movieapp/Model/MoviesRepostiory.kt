package com.example.movieapp.Model

import androidx.lifecycle.MutableLiveData
import com.example.movieapp.Model.remote.MovieService
import kotlinx.coroutines.withContext
import retrofit2.Response

class MoviesRepostiory {

    val moviesLiveData = MutableLiveData<List<Movie>>()
    suspend fun getMovies(query: String): Response<MoviesResponse> =

            MovieService.getMovieService().getMovies("32bdbd740a2356800eff3503e0f1217f", query)

//                ?.let {
//                    Log.i("TAG", "getMovies: "+ (it.get(0).title))
//
//                }

    suspend fun getVideo(id: Int): Response<VideoDataClass> =
            MovieService.getMovieService().getVideo("32bdbd740a2356800eff3503e0f1217f", id)

}