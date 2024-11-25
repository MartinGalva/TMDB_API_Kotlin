package com.example.segundoparcialgalvarini

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class Adapter(val context: Context) : ListAdapter<Movie, Adapter.ViewHolder>(DiffCallBack) {
    var onItemClickListener: ((Movie) -> Unit)? = null

    inner class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.findViewById(R.id.imageView)
        private val tvMovieTitle: TextView = view.findViewById(R.id.tv_movieTitle)

        fun bind(movie: Movie) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
                .into(imageView)
            tvMovieTitle.text = movie.title

            view.setOnClickListener {
                onItemClickListener?.invoke(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}