package com.capstone.fokusin.ui.onboarding.screen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.capstone.fokusin.R
import com.capstone.fokusin.databinding.FragmentThrScreenBinding


class ThrScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =  FragmentThrScreenBinding.inflate(inflater, container, false)
        val viewPgr = activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.tvFinish.setOnClickListener {
            findNavController().navigate(R.id.vp_to_home)
            onBoardFinish()
        }
        return view
    }

    private fun onBoardFinish() {
        val sharePref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val edt = sharePref.edit()
        edt.putBoolean("Finished", true)
        edt.apply()
    }


}