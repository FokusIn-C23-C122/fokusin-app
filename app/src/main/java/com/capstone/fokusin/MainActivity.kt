package com.capstone.fokusin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import com.capstone.fokusin.databinding.ActivityMainBinding
import com.capstone.fokusin.ui.activity.LoginScreen
import com.capstone.fokusin.ui.activity.StatisticActivity
import com.capstone.fokusin.ui.screen.SettingScreen
import com.ismaeldivita.chipnavigation.ChipNavigationBar

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val chipNavigationBar: ChipNavigationBar = findViewById(R.id.nav)
        chipNavigationBar.setItemSelected(R.id.navHom)
        chipNavigationBar.setOnItemSelectedListener { id ->
            when (id) {
                R.id.navHom -> {
                    // Logika untuk item navigasi 1
                }
                R.id.navCam -> {
                        Intent(this, LoginScreen::class.java).also {
                            startActivity(it)
                        }
                }
                R.id.navStat -> {
                    Intent(this, StatisticActivity::class.java).also {
                        startActivity(it)
                    }
                }
            }
        }


    }

}