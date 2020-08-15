package com.example.gifapisimpleapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gifapisimpleapplication.AppComponent
import com.example.gifapisimpleapplication.R
import com.example.gifapisimpleapplication.viewmodels.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment(val isFavorite : Boolean) : BaseFragment() {

    override val viewModel: FeedViewModel by viewModels { AppComponent.viewModelFactory }

    companion object {
        const val ARG_POSITION = "position"

        fun getInstance(position: Int, isFavorite: Boolean): Fragment {
            val feedFragment = FeedFragment(isFavorite)
            val bundle = Bundle()
            bundle.putInt(ARG_POSITION, position)
            feedFragment.arguments = bundle
            return feedFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        list_gifs.apply {
            layoutManager = LinearLayoutManager(
                this.context,
                LinearLayoutManager.VERTICAL,
                false
            )
            this.adapter = viewModel.feedItemsAdapter
        }
        viewModel.init(isFavorite)
    }

}