package com.example.movieapp.screens.main

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.MAIN
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMainBinding
import com.example.movieapp.models.MovieItemModel


@Suppress("DEPRECATION")
class MainFragment : Fragment() {

    private var mBinding: FragmentMainBinding? = null
    private val binding get() = mBinding!!
    private lateinit var recyclerView: RecyclerView
    private val adapter by lazy { MainAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,

        ): View {
        mBinding = FragmentMainBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[MainFragmentViewModel::class.java]
        viewModel.getMoviesRetrofit()
        viewModel.initDatabase()
        recyclerView = binding.rvMain
        recyclerView.adapter = adapter
        binding.toolbar.menu.findItem(R.id.item_favorite).setOnMenuItemClickListener {
            MAIN.navController.navigate(R.id.action_homeFragment_to_favoriteFragment)
            true
        }
        try {
            viewModel.getMoviesRetrofit()
            viewModel.myMovies.observe(viewLifecycleOwner) { list ->
                adapter.setList(list.body()!!.results)
            }

        } catch (e: Throwable) {
            Log.e("ERROR", e.message.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    companion object {
        fun clickMovie(model: MovieItemModel) {
            val bundle = Bundle()
            bundle.putSerializable("movie", model)
            MAIN.navController.navigate(R.id.action_homeFragment_to_detailFragment, bundle)
//            val locale = Locale("uk")
//            Locale.setDefault(locale)
//            val resources: Resources = MAIN.resources
//            val config: Configuration = resources.getConfiguration()
//            config.setLocale(locale)
//            resources.updateConfiguration(config, resources.getDisplayMetrics())
        }
    }


    }

