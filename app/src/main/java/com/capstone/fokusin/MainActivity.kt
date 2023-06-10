package com.capstone.fokusin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.fragment.app.Fragment
import com.capstone.fokusin.databinding.ActivityMainBinding
import com.capstone.fokusin.ui.activity.LoginScreen
import com.capstone.fokusin.ui.activity.StatisticActivity
import com.capstone.fokusin.ui.fragment.HomeFragment
import com.capstone.fokusin.ui.fragment.RecordFragment
import com.capstone.fokusin.ui.fragment.SettingFragment
import com.capstone.fokusin.ui.fragment.StatistikFragment
import com.capstone.fokusin.ui.screen.SettingScreen
import com.ismaeldivita.chipnavigation.ChipNavigationBar

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    val fragment = HomeFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lodFragment(HomeFragment())
        val chipNavigationBar: ChipNavigationBar = findViewById(R.id.nav)
        chipNavigationBar.setItemSelected(R.id.navHom)
        chipNavigationBar.setOnItemSelectedListener { id ->
            when (id) {
                R.id.navHom -> {
                    lodFragment(HomeFragment())
                }
                R.id.navCam -> {
//                    Intent(this, LoginScreen::class.java).also {
//                        startActivity(it)
//                    }
                    lodFragment(RecordFragment())
                }
                R.id.navStat -> {
                    lodFragment(StatistikFragment())
                }
            }
        }


    }
    private fun lodFragment(fragment: Fragment){
        val trans = supportFragmentManager.beginTransaction()
        trans.replace(R.id.konten,fragment)
        trans.commit()
    }

}