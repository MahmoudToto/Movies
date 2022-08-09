package com.example.movies.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.LocalDB.BaseApplication
import com.example.movies.Pojo.Const
import com.example.movies.Pojo.LatestMovies.LatestMovies
import com.example.movies.Pojo.Movies.Movies
import com.example.movies.Pojo.Movies.Result
import com.example.movies.R
import java.util.*

class MoviesLatestAdapter : RecyclerView.Adapter<MoviesLatestAdapter.ViewHolder>() {
    var movieslist: List<Result> = emptyList()


    fun setList(data: List<Result>) {
        this.movieslist = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesLatestAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_movieslatest, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesLatestAdapter.ViewHolder, position: Int) {
        var data: Result = movieslist[position]
        holder.setId(data)
    }

    override fun getItemCount(): Int {
        return movieslist.size
    }


    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var img: ImageView = itemView.findViewById(R.id.id_latestmovies)
        var titel: TextView = itemView.findViewById(R.id.name_of_latestmovies)

        fun setId(data: Result) {
            titel.text = data.title
            Glide.with(img.context)
                .load(Const.BASE_URL_IMG + data.poster_path)
                .into(img)

        }

    }
}