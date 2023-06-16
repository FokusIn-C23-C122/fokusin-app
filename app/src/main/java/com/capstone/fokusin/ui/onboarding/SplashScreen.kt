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
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }, DELAY)
    }


}