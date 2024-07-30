package com.erayerarslan.tmdbmovieapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.erayerarslan.tmdbmovieapp.R
import com.erayerarslan.tmdbmovieapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieAdapter: MovieAdapter

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel.getMoviesList()
        observeEvents()

        return binding.root
    }

    private fun observeEvents() {
        viewModel.errorMessage.observe(viewLifecycleOwner) { error->
            binding.textViewHomeError.text = error
            binding.textViewHomeError.isVisible = true
        }
        viewModel.isLoading.observe(viewLifecycleOwner){ loading ->
            binding.progressBar.isVisible = loading
        }
        viewModel.movieList.observe(viewLifecycleOwner) { list ->
            if (list.isNullOrEmpty()) {
                binding.textViewHomeError.text = "There is any movie :("
                binding.textViewHomeError.isVisible = true
            }else{
                movieAdapter = MovieAdapter(list,object : MovieClickListener{
                    override fun onMovieClicked(movieId: Int?) {
                        movieId?.let {
                            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
                            findNavController().navigate(action)
                        }
                    }
                })
                binding.homeRecyclerView.adapter = movieAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}