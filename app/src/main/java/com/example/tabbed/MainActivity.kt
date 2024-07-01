package com.example.tabbed

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.tabbed.R
import com.example.tabbed.ScreenSlide

/**
 * The number of pages (wizard steps) to show in this demo.
 */

class MainActivity : AppCompatActivity() {

    lateinit var fragment : MainFrag

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragment = MainFrag() //fragment with viewPager2
        val fragManager : FragmentManager = supportFragmentManager //manager to transact
        val fragTran : FragmentTransaction = fragManager.beginTransaction() //start transaction
        fragTran.replace(R.id.frameLayout,fragment) // frameLayout to contain the fragment
        fragTran.commit() //end transaction

    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() = fragment.moveToPreviousPage()



}


