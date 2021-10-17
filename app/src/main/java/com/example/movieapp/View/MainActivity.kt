package com.example.movieapp.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.Model.Movie
import com.example.movieapp.Model.MoviesRepostiory
import com.example.movieapp.R
import com.example.movieapp.ViewModel.MoviesViewModel
import com.example.movieapp.ViewModel.MoviesViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() ,MovieAdapter.OnViewClickListener {
    var movieAdapter =
            MovieAdapter(arrayListOf())
    val moviesViewModel:MoviesViewModel by viewModels {
        MoviesViewModelFactory(moviesRepostiory = MoviesRepostiory())

    }
    var movies : List<Movie>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        moviesViewModel.getMovies("Thor")
         movies  = moviesViewModel.movies.value


        val recyclerView: RecyclerView =findViewById(R.id.recyclerView)
        var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager= linearLayoutManager
        recyclerView.adapter=movieAdapter

        moviesViewModel.getLiveData().value?.let { movieAdapter.updateList(it) }

       moviesViewModel.getLiveData().observe(this, {
           moviesViewModel.movies.value?.let { it ->
               movieAdapter.updateList(it)
               movieAdapter.setOnClickListener(this)
           }
       })

        searchView.setIconifiedByDefault(false)
        searchView.setQueryHint("Thor")
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                Log.i("TAG", "onQueryTextChange: $newText")
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // task HERE
                Log.i("TAG", "onQueryTextSubmit: $query")
                moviesViewModel.getMovies(query)
                movies = moviesViewModel.movies.value
                if (movieAdapter != null) {
                    movies?.let { it.let { it1 -> movieAdapter.updateList(it1) } }
                    Log.i("TAG", "onQueryTextSubmit: not")

                } else {
                    Log.i("TAG", "onQueryTextSubmit: nil")
                }
                return false
            }
        })

    }

    override fun navigateToDetails(position: Int) {
        val intent = Intent(this, MovieeDetails::class.java)
        intent.putExtra("movie", movieAdapter.movies[position])

        Log.i("TAG", "navigateToDetails: ${movieAdapter.movies[position].title}")
         startActivity(intent)
//        Log.i("TAG", "navigateToDetails: ")
//        var f: FragmentManager? = null
//        val fragment = MovieDetails()
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.main, fragment)
//        transaction.commit()
////        f = supportFragmentManager
////        fragmentB = MovieDetails()
////        f.beginTransaction().add(R.id.main, fragmentB, "fragment").commit()
    }

}
