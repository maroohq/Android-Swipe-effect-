package com.example.tabbed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
class MainFrag: Fragment() {
    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    private lateinit var slideAdapter: SlideAdapter
    public lateinit var viewPager: ViewPager2

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        slideAdapter = SlideAdapter(this)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = slideAdapter

        val arr : ArrayList<String> = arrayListOf("Today","5-day","City Weather")

        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->

            tab.text = arr.get(position)
        }.attach()

    }

    fun moveToFirst() {
        //Work on moving to first page
        if (viewPager.currentItem != 0){
            viewPager.currentItem = 0
        }
        else {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
    }

    fun moveToPreviousPage() {
        //Work on moving to first page
        if (viewPager.currentItem != 0){
            viewPager.currentItem -= 1
        }
        else {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
    }




}
