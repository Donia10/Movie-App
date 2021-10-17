package com.example.movieapp.View

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movieapp.Constants
import com.example.movieapp.Constants.YOUTUBE_VIDEO_URL
import com.example.movieapp.Model.Movie
import com.example.movieapp.Model.MoviesRepostiory
import com.example.movieapp.R
import com.example.movieapp.ViewModel.MoviesViewModel
import com.example.movieapp.ViewModel.MoviesViewModelFactory
import kotlinx.android.synthetic.main.activity_moviee_details.*
import kotlinx.android.synthetic.main.fragment_movie_details.averg
import kotlinx.android.synthetic.main.fragment_movie_details.imagePoster
import kotlinx.android.synthetic.main.fragment_movie_details.titleM
import java.text.SimpleDateFormat

class MovieeDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moviee_details)
        val viewModel: MoviesViewModel by viewModels {
            MoviesViewModelFactory(moviesRepostiory = MoviesRepostiory())

        }
        val bundle = intent.extras
        var movie = bundle?.get("movie") as Movie

        titleM.text = movie.title
        titleM.setTypeface(null, Typeface.BOLD);

        if (movie.releaseDate != null) {
            release.text = movie.releaseDate
            val strCurrentDate = "Wed, 18 Apr 2012 07:55:29 +0000"
            var format = SimpleDateFormat("yyyy-MM-dd")
            val newDate = format.parse(movie.releaseDate)

            format = SimpleDateFormat("dd/MM/yyyy")
            val date = format.format(newDate)
            format = SimpleDateFormat("yyyy")
            val year = format.format(newDate)
            release.text = date
            yearId.text = "($year)"

        }

        averg.text = movie.voteAverage
        overviewM.text = movie.overview
        val imageUrl =  Constants.BASE_POSTER_URL + movie.posterpath
        val options = RequestOptions()
            .error(R.mipmap.ic_launcher_round)
        Glide.with(imagePoster.context)
            .setDefaultRequestOptions(options)
            .load(imageUrl)
            .into(imagePoster)
        val backdropURL =  Constants.BASE_POSTER_URL + movie.backdropPath

        Glide.with(backdrop.context)
            .setDefaultRequestOptions(options)
            .load(backdropURL)
            .into(backdrop)

        Glide.with(img.context)
                .setDefaultRequestOptions(options)
                .load(backdropURL)
                .into(img)

        Log.i("TAG", "onCreate: $title")

        if (movie.video == true){
            video.visibility = View.VISIBLE
            play.visibility = View.VISIBLE
            Glide.with(video.context)
                    .setDefaultRequestOptions(options)
                    .load(imageUrl)
                    .into(video)
            video.setOnClickListener(View.OnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(YOUTUBE_VIDEO_URL+viewModel.getVideo(movie.id))))
            })
        }
        back.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })
    }
}