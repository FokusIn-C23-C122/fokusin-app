package com.capstone.fokusin.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.capstone.fokusin.R
import com.capstone.fokusin.databinding.ActivitySignupScreenBinding

class SignupScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySignupScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toreg: TextView = findViewById(R.id.tv_log)
        toreg.setOnClickListener {
            Intent(this, LoginScreen::class.java).also {
                startActivity(it)
            }
        }
    }
}