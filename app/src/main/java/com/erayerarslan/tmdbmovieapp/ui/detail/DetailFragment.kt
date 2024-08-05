package com.erayerarslan.tmdbmovieapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.erayerarslan.tmdbmovieapp.R
import com.erayerarslan.tmdbmovieapp.databinding.FragmentDetailBinding
import com.erayerarslan.tmdbmovieapp.util.loadImage


class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DetailViewModel>()
    private val args by navArgs<DetailFragmentArgs>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding=FragmentDetailBinding.inflate(inflater,container,false)

        viewModel.getMovieDetail(movieId = args.movieId)

        observeEvents()

        return binding.root
    }


    private fun observeEvents(){

        viewModel.isLoading.observe(viewLifecycleOwner){
            binding.progressBar2.isVisible=it
        }
        viewModel.errorMesssage.observe(viewLifecycleOwner) {
            binding.textViewErrorDetail.text = it
            binding.textViewErrorDetail.isVisible = true
        }

        viewModel.movieResponse.observe(viewLifecycleOwner) { movie ->
            val voteScore = String.format("%.1f", movie?.voteAverage)

            binding.imageViewDetail.loadImage(movie.backdropPath)
            binding.textViewDetailVote.text = voteScore
            binding.textViewDetailStudio.text=movie.productionCompanies?.firstOrNull()?.name
            binding.textViewDetailLanguage.text=movie.spokenLanguages?.firstOrNull()?.englishName
            binding.textViewDetail.text = movie.overview
            binding.movieDetailGroup.isVisible = true
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}