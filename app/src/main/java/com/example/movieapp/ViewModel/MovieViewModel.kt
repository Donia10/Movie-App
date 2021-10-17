package com.example.movieapp.ViewModel

import android.util.Log
import androidx.lifecycle.*
import com.example.movieapp.Model.Movie
import com.example.movieapp.Model.MoviesRepostiory
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

class MoviesViewModel(private val moviesRepostiory: MoviesRepostiory): ViewModel() {

    var movies = MutableLiveData<List<Movie>>()
    var videoKey:String? = ""
    fun getLiveData():LiveData<List<Movie>>{
        return  movies
    }
    fun getMovies(query:String) = viewModelScope.launch {

        if (moviesRepostiory.getMovies(query).isSuccessful) {
            movies.value = moviesRepostiory.getMovies(query).body()?.results!!
            Log.i("TAG", "getMovies:${movies.value?.get(0)?.title} ")
        }else {
            Log.i("TAG", "getMovies: failed")
        }
    }
    fun getVideo(id: Int): String? {

        viewModelScope.launch {
            async {
                if (moviesRepostiory.getVideo(id).isSuccessful) {
                    videoKey = moviesRepostiory.getVideo(id).body()?.results?.get(0)?.key
                }
            }.await()

        }
        return videoKey
    }

}
class MoviesViewModelFactory(private val moviesRepostiory: MoviesRepostiory):
    ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MoviesViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return MoviesViewModel(
                moviesRepostiory
            ) as T
        }
        throw IllegalArgumentException("UnKnown ViewModel class")
    }

}