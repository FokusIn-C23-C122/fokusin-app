package com.capstone.fokusin.ui.onboarding

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.capstone.fokusin.MainActivity
import com.capstone.fokusin.R
import com.capstone.fokusin.ui.fragment.ViewPagerFragment

class SplashScreen : AppCompatActivity() {

    private val DELAY: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
//            if(onBoardingFinished()){
//                findNavController().navigate(R.id.splashScreen to R.id.homeeActivity)
//            }else{
//                findNavController().navigate(R.id.splashScreen to R.id.viewPagerFragment)
//            }
            if (onBoardingFinished()) {


                val navController = Navigation.findNavController(this, R.id.splashScreen)
                navController.navigate(R.id.viewPagerFragment)
            }
            else{
                print("cobalagi")
            }
        }, DELAY)
    }

    private fun onBoardingFinished(): Boolean{
        val sharePref = getSharedPreferences("onBoard", Context.MODE_PRIVATE)

        return sharePref.getBoolean("finished", false)
    }
}