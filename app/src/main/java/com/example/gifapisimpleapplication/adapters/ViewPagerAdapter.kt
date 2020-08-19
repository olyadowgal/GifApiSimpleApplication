package com.example.gifapisimpleapplication.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.gifapisimpleapplication.fragments.FavoritesFragment
import com.example.gifapisimpleapplication.fragments.FeedFragment
import java.lang.IllegalStateException

class ViewPagerAdapter(activity: AppCompatActivity, val itemsCount: Int) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return itemsCount
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FeedFragment.getInstance()
            1 -> FavoritesFragment.getInstance()
            else -> throw IllegalStateException()
        }

    }

}