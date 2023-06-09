package com.capstone.fokusin.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.capstone.fokusin.MainActivity
import com.capstone.fokusin.R
import com.capstone.fokusin.databinding.ActivityLoginScreenBinding

class LoginScreen : AppCompatActivity() {
    private lateinit var binding: ActivityLoginScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnlog: Button = findViewById(R.id.btn_log)
        btnlog.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
        }
        val toreg: TextView = findViewById(R.id.tv_reg)
        toreg.setOnClickListener {
            Intent(this, SignupScreen::class.java).also {
                startActivity(it)
            }
        }
    }
}