package com.example.gifapisimpleapplication.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gifapisimpleapplication.R
import com.example.gifapisimpleapplication.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabsNamesArray = resources.getStringArray(R.array.tabs_names)

        val feedAdapter = ViewPagerAdapter(this, tabsNamesArray.size)
        feed_pager.adapter = feedAdapter

        TabLayoutMediator(feed_tabs, feed_pager) { tab, position ->
            tab.text = tabsNamesArray[position]
        }.attach()
    }
}