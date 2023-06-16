package com.capstone.fokusin.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.capstone.fokusin.R
import com.capstone.fokusin.data.sharepr.PrefHelper
import com.capstone.fokusin.databinding.ActivitySignupScreenBinding
import com.capstone.fokusin.ui.fragment.HomeFragment

//class SignupScreen : AppCompatActivity() {
//    private lateinit var binding: ActivitySignupScreenBinding
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivitySignupScreenBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        val toreg: TextView = findViewById(R.id.tv_log)
//        toreg.setOnClickListener {
//            Intent(this, LoginScreen::class.java).also {
//                startActivity(it)
//            }
//        }
//    }
//}

class SignupScreen : AppCompatActivity() {
    private lateinit var sharedPreferenceHelper: PrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_screen)
        val btnreg: Button = findViewById(R.id.btn_reg)
        val eduser: EditText = findViewById(R.id.ed_username)
        val edemail: EditText = findViewById(R.id.ed_email)
        val edpw: EditText = findViewById(R.id.ed_password)
        sharedPreferenceHelper = PrefHelper(this)

        btnreg.setOnClickListener {
            val username = eduser.text.toString()
            val email = edemail.text.toString()
            val password = edpw.text.toString()

            // Lakukan validasi registrasi sesuai kebutuhan, misalnya dengan memeriksa apakah username atau email sudah digunakan sebelumnya
            if (isValidRegistration(username, email, password)) {
                sharedPreferenceHelper.saveUser(username, email)
                startHomeActivity(username, email)
            } else {
                Toast.makeText(this, "Registrasi gagal. Silakan cek data yang diinput.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidRegistration(username: String, email: String, password: String): Boolean {
        // Implementasikan validasi registrasi sesuai kebutuhan Anda, misalnya dengan memeriksa apakah username atau email sudah digunakan sebelumnya
        return true
    }

    private fun startHomeActivity(username: String, email: String) {
        val fragment = HomeFragment()
        val bundle = Bundle()
        bundle.putString("username", username)
        bundle.putString("email", email)
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.konten, fragment)
            .commit()
    }

}
