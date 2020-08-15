package com.example.gifapisimpleapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.gifapisimpleapplication.R
import com.example.gifapisimpleapplication.fragments.FeedFragment
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_feed_fragment.view.*

class FeedAdapter (activity: AppCompatActivity, val itemsCount: Int) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return itemsCount
    }

    override fun createFragment(position: Int): Fragment {
        return FeedFragment.getInstance(position)
    }

}