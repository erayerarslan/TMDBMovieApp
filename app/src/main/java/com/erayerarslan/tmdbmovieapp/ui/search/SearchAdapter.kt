package com.erayerarslan.tmdbmovieapp.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.erayerarslan.tmdbmovieapp.databinding.ItemHomeRecyclerViewBinding
import com.erayerarslan.tmdbmovieapp.model.MovieItem
import com.erayerarslan.tmdbmovieapp.ui.home.MovieClickListener
import com.erayerarslan.tmdbmovieapp.util.loadCircleImage



class SearchAdapter (
    private val movieList: List<MovieItem?>,
    private val movieClickListener: MovieClickListener,

    )
    : RecyclerView.Adapter<SearchAdapter.ViewHolder>(){
        class ViewHolder(val binding: ItemHomeRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(ItemHomeRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }

        override fun getItemCount(): Int {
            return movieList.size
        }

        @SuppressLint("DefaultLocale")
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val movie = movieList[position]

            //puanı yuvaladık ve bir değere atadık
            val voteScore = String.format("%.1f", movie?.voteAverage)
            val context = holder.binding.root.context
            val movieTitle = movie?.title
            holder.binding.apply {
                this.textViewMovieTitle.text = movieTitle
                this.textViewMovieOverview.text= movie?.overview
                this.textViewMovieVote.text = voteScore

                this.imageViewMovie.loadCircleImage(movie?.posterPath)

                this.root.setOnClickListener {
                    movieClickListener.onMovieClicked(movieId = movie?.id)
                }


                this.imageView2.setOnClickListener {
                    Toast.makeText(context, voteScore, Toast.LENGTH_SHORT).show()
                }
                this.imageViewMovie.setOnLongClickListener {
                    Toast.makeText(context, "$movieTitle filmini daha önce izlediniz mi?", Toast.LENGTH_SHORT).show()
                    true
                }

            }



        }


    }
