package com.example.movieapp.screens.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.MAIN
import com.example.movieapp.databinding.FragmentFavoriteBinding
import com.example.movieapp.screens.main.MainAdapter
import kotlinx.android.synthetic.main.fragment_detail.view.*

class FavoriteFragment : Fragment() {

    private var mBinding: FragmentFavoriteBinding ?= null
    private val binding get() = mBinding!!
    lateinit var recyclerView: RecyclerView
    private val adapter by lazy { MainAdapter() }

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
        val viewModel = ViewModelProvider(this).get(FavoriteFragmentViewModel::class.java)
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
}