package com.capstone.fokusin.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.capstone.fokusin.R
import com.capstone.fokusin.data.sharepr.PrefHelper
import com.capstone.fokusin.ui.activity.LoginScreen


class HomeFragment : Fragment() {
    private lateinit var sharedPreferenceHelper: PrefHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        sharedPreferenceHelper = PrefHelper(requireContext())

        val username = arguments?.getString("username")
        val email = arguments?.getString("email")

        if (username != null && email != null) {
            showWelcomeMessage(username, email)
        }
        val iv: ImageView = view.findViewById(R.id.setting)
        val ibtn: Button = view.findViewById(R.id.startButton)
        val nbtn: Button = view.findViewById(R.id.btn_next)
        iv.setOnClickListener {
            lodFragment(SettingFragment())
        }
        ibtn.setOnClickListener {
            val intent = Intent(activity, LoginScreen::class.java)
            startActivity(intent)
        }
        nbtn.setOnClickListener {
            lodFragment(StatistikFragment())
        }
        return view
    }

    private fun lodFragment(fragment: Fragment) {
        val trans = parentFragmentManager.beginTransaction()
        trans.replace(R.id.konten, fragment)
        trans.commit()
    }

    private fun showWelcomeMessage(username: String, email: String) {
        val welcomeMessage = "Selamat datang, $username\nEmail: $email"
        Toast.makeText(requireContext(), welcomeMessage, Toast.LENGTH_SHORT).show()
    }

}