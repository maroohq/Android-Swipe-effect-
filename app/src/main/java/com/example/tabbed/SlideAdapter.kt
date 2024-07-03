package com.example.tabbed

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

private const val ARG_OBJECT = "object"
private  lateinit var fragment :Fragment

class SlideAdapter(val frag:Fragment) :FragmentStateAdapter(frag) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {

         fragment = ScreenSlide()

        if (position == 1){
            fragment = WeekForecastFragment()
        }

        fragment.arguments = Bundle().apply {
            putInt(ARG_OBJECT, position + 1) // The object is just an integer.

        }
        return fragment
    }
}


