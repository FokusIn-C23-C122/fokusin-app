package com.capstone.fokusin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.capstone.fokusin.R


class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val ibtn: ImageView = view.findViewById(R.id.setting)

        ibtn.setOnClickListener {
            lodFragment(SettingFragment())
        }
        return view
    }
    private fun lodFragment(fragment: Fragment) {
        val trans = parentFragmentManager.beginTransaction()
        trans.replace(R.id.konten, fragment)
        trans.commit()
    }

}