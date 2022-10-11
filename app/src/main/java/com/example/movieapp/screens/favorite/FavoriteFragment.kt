package com.example.movieapp.screens.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.MAIN
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentFavoriteBinding
import com.example.movieapp.models.MovieItemModel

class FavoriteFragment : Fragment() {

    private var mBinding: FragmentFavoriteBinding ?= null
    private val binding get() = mBinding!!
    private lateinit var recyclerView: RecyclerView
    private val adapter by lazy { FavoriteAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[FavoriteFragmentViewModel::class.java]
        viewModel.getAllMovies().observe(viewLifecycleOwner) { list ->
            adapter.setList(list.asReversed())
        }
        recyclerView  = binding.rvFavorite
        recyclerView.adapter = adapter
        binding.toolbar.setNavigationOnClickListener {
            MAIN.navController.popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
    companion object {
        fun clickMovie(model: MovieItemModel){
            val bundle = Bundle()
            bundle.putSerializable("movie", model)
            MAIN.navController.navigate(R.id.action_favoriteFragment_to_detailFragment, bundle)
        }
    }
}