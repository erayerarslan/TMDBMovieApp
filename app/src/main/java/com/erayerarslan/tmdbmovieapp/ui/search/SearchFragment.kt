package com.erayerarslan.tmdbmovieapp.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.erayerarslan.tmdbmovieapp.R
import com.erayerarslan.tmdbmovieapp.databinding.FragmentDetailBinding
import com.erayerarslan.tmdbmovieapp.databinding.FragmentSearchBinding
import com.erayerarslan.tmdbmovieapp.ui.home.HomeFragmentDirections
import com.erayerarslan.tmdbmovieapp.ui.home.MovieAdapter
import com.erayerarslan.tmdbmovieapp.ui.home.MovieClickListener


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var searchAdapter: SearchAdapter
    private val viewModel by viewModels<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        viewModel.fetchMovies(query = null)
        observeEvents()

        return binding.root
    }

    private fun observeEvents() {

        viewModel.movieList.observe(viewLifecycleOwner) { list ->
            if (list.isNullOrEmpty()) {

                binding.editTextSearch.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {}
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        val query = s.toString().trim()
                        viewModel.fetchMovies(query)


                    }
                })
            } else {

                searchAdapter = SearchAdapter(list, object : MovieClickListener {
                    override fun onMovieClicked(movieId: Int?) {
                        movieId?.let {
                            val action =
                                SearchFragmentDirections.actionSearchFragmentToDetailFragment(it)
                            findNavController().navigate(action)
                        }
                    }
                })
                binding.SearchRecyclerView.adapter = searchAdapter


            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

