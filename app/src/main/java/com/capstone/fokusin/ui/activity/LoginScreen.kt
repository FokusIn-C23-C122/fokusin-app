package com.capstone.fokusin.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.capstone.fokusin.MainActivity
import com.capstone.fokusin.R
import com.capstone.fokusin.data.response.LoginResponse
import com.capstone.fokusin.data.response.LoginRess
//import com.capstone.fokusin.data.response.Loginresp
import com.capstone.fokusin.data.service.FokConfig
import com.capstone.fokusin.data.service.FokService
import com.capstone.fokusin.data.sharepr.PrefHelper
import com.capstone.fokusin.databinding.ActivityLoginScreenBinding
import com.capstone.fokusin.ui.fragment.HomeFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

//class LoginScreen : AppCompatActivity() {
//    private lateinit var binding: ActivityLoginScreenBinding
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val btnlog: Button = findViewById(R.id.btn_log)
//        btnlog.setOnClickListener {
//            Intent(this, MainActivity::class.java).also {
//                startActivity(it)
//            }
//        }
//        val toreg: TextView = findViewById(R.id.tv_reg)
//        toreg.setOnClickListener {
//            Intent(this, SignupScreen::class.java).also {
//                startActivity(it)
//            }
//        }
//    }
//}

//class LoginScreen : AppCompatActivity() {
//
//    private lateinit var usernameEditText: EditText
//    private lateinit var passwordEditText: EditText
//    private lateinit var loginButton: Button
//
//    private lateinit var fokService: FokService
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login_screen)
//
//        usernameEditText = findViewById(R.id.ed_username)
//        passwordEditText = findViewById(R.id.ed_password)
//        loginButton = findViewById(R.id.btn_log)
//
//        fokService = FokConfig.getApiServiceFok()
//
//        loginButton.setOnClickListener {
//            val username = usernameEditText.text.toString()
//            val password = passwordEditText.text.toString()
//
//            // Validasi input kosong
//            if (username.isNotEmpty() && password.isNotEmpty()) {
//                // Panggil fungsi login
//                performLogin(username, password)
//            } else {
//                Toast.makeText(this, "Mohon lengkapi semua field", Toast.LENGTH_SHORT).show()
//            }
//        }
//        val toreg: TextView = findViewById(R.id.tv_reg)
//        toreg.setOnClickListener {
//            Intent(this, SignupScreen::class.java).also {
//                startActivity(it)
//            }
//        }
//    }
//
//    private fun performLogin(username: String, password: String) {
//        // Menjalankan permintaan login pada background thread menggunakan Coroutine
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                // Panggil API login menggunakan Retrofit
//                // Panggil API login menggunakan Retrofit
//                val response: Response<LoginRess> = fokService.login(username, password)
//
//                if (response.isSuccessful) {
//                    val loginResponse: LoginRess? = response.body()
//                    if (loginResponse != null) {
//                        val tokens = loginResponse.tokens
//                        val username = loginResponse.username
//                        // SharedPreference
//
//                        // Tampilkan pesan sukses atau navigasi ke halaman berikutnya
//                        runOnUiThread {
//                            Toast.makeText(this@LoginScreen, "Login berhasil", Toast.LENGTH_SHORT).show()
//                            // Navigasi ke halaman utama
//                             val intent = Intent(this@LoginScreen, MainActivity::class.java)
//                             startActivity(intent)
//                             finish()
//                        }
//                    } else {
//                        // Login gagal
//                        runOnUiThread {
//                            Toast.makeText(this@LoginScreen, "Login gagal", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                } else {
//                    runOnUiThread {
//                        Toast.makeText(this@LoginScreen, "Terjadi kesalahan pada server", Toast.LENGTH_SHORT).show()
//                    }
//                }
//
//            } catch (e: Exception) {
//                e.printStackTrace()
//                runOnUiThread {
//                    Toast.makeText(this@LoginScreen, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }
//}
class LoginScreen : AppCompatActivity() {
    private lateinit var sharedPreferenceHelper: PrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        val btnlog: Button = findViewById(R.id.btn_log)
        val eduser: EditText = findViewById(R.id.ed_username)
        val edpw: EditText = findViewById(R.id.ed_password)
        sharedPreferenceHelper = PrefHelper(this)
        val toreg: TextView = findViewById(R.id.tv_reg)
        toreg.setOnClickListener {
            Intent(this, SignupScreen::class.java).also {
                startActivity(it)
            }
        }
        btnlog.setOnClickListener {
            val username = eduser.text.toString()
            val password = edpw.text.toString()

            // Lakukan validasi login sesuai kebutuhan, misalnya dengan membandingkan dengan data di server
            if (isValidLogin(username, password)) {
                val user = sharedPreferenceHelper.getUser()
                val savedUsername = user.first
                val savedEmail = user.second

                if (savedUsername != null && savedEmail != null) {
                    startHomeActivity(savedUsername, savedEmail)
                }
            } else {
                Toast.makeText(this, "Login gagal. Silakan cek username dan password.", Toast.LENGTH_SHORT).show()
            }
        }

        btnlog.setOnClickListener {
            val intent = Intent(this, SignupScreen::class.java)
            startActivity(intent)
        }
    }

    private fun isValidLogin(username: String, password: String): Boolean {
        // Implementasikan validasi login sesuai kebutuhan Anda, misalnya dengan membandingkan dengan data di server
        return username == "username" && password == "password"
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

