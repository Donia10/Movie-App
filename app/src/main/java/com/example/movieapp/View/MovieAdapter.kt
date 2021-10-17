package com.example.movieapp.View

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movieapp.Constants.BASE_POSTER_URL
import com.example.movieapp.Model.Movie
import com.example.movieapp.R
import kotlinx.android.synthetic.main.movie_row.view.*

class MovieAdapter(var movies: ArrayList<Movie>):RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    lateinit var listener: MovieAdapter.OnViewClickListener
    private lateinit var  context: Context

    fun updateList(newsMovies: List<Movie>) {
        movies.clear()
        movies.addAll(newsMovies)
        notifyDataSetChanged()
    }

    interface OnViewClickListener {
        fun navigateToDetails(position: Int)

    }

    fun setOnClickListener( listenerr: OnViewClickListener){listener=listenerr}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MovieViewHolder{
    val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_row, parent, false)
    context=parent.context
    return MovieViewHolder(view,listener)
}
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies.get(position))
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class MovieViewHolder(itemView: View,val listener:OnViewClickListener) : RecyclerView.ViewHolder(itemView) {

        private val title = itemView.movieTitle
        private val releaseDate = itemView.movieReleaseDate
        private val description = itemView.movieDescription
        private val image = itemView.movieImage
        private val totalAverage = itemView.totalAverage
        fun bind(movie: Movie){
            title.text = movie.title
            releaseDate.text = movie.releaseDate
            description.text = movie.overview
            totalAverage.text = movie.voteAverage


            val imageUrl =  BASE_POSTER_URL + movie.posterpath
            val options = RequestOptions()
                .error(R.mipmap.ic_launcher_round)
            Glide.with(image.context)
                .setDefaultRequestOptions(options)
                .load(imageUrl)
                .into(image)
            itemView.setOnClickListener(View.OnClickListener {
                if(listener!=null) {
                    val position: Int = adapterPosition
                    if (position!=RecyclerView.NO_POSITION) {
                        listener.navigateToDetails(position)
                    }
                }
            })

        }


    }

}