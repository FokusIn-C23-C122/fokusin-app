package com.capstone.fokusin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.capstone.fokusin.databinding.FragmentViewPagerBinding
import com.capstone.fokusin.ui.adapter.ViewPagerAdapter
import com.capstone.fokusin.ui.onboarding.screen.FirstScreen
import com.capstone.fokusin.ui.onboarding.screen.SecondScreen
import com.capstone.fokusin.ui.onboarding.screen.ThrScreen

class ViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val view = binding.root

        val fragList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThrScreen()
        )
        val adapter = ViewPagerAdapter(
            fragList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        binding.viewPager.adapter = adapter

        return view
    }



}