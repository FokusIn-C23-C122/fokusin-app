package com.capstone.fokusin.ui.fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.capstone.fokusin.R


class SplashScreen : Fragment() {
    private val DELAY: Long = 3000
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Handler().postDelayed({
            if (onBoardingFinish()){
                findNavController().navigate(R.id.splashToHome)
            }else{
                findNavController().navigate(R.id.splashToVp)
            }
        }, DELAY)

        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    private fun onBoardingFinish(): Boolean {
        val shPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return shPref.getBoolean("Finished", false)
    }
}