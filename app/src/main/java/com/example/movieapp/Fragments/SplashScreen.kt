package com.example.movieapp.Fragments

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R


@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment() {


    private fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return if (networkInfo != null && networkInfo.isConnected) {
            return true
        } else {
            false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_splash_screen, container, false)
        Handler(Looper.myLooper()!!).postDelayed({
            if(isNetworkAvailable(requireActivity())){
                findNavController().navigate(R.id.action_splashScreen_to_homeFragment)
            }
//            else if(isNetworkAvailable(requireActivity())){
//                findNavController().navigate(R.id.action_noInternet_to_homeFragment)
//            }
            else{
                findNavController().navigate(R.id.action_splashScreen_to_noInternet)
                Toast.makeText(requireActivity(), "Нет подключения к интернету", Toast.LENGTH_LONG).show()
        }
        }, 1500)

        return view
    }
}