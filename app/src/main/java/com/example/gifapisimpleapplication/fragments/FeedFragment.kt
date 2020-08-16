package com.example.gifapisimpleapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gifapisimpleapplication.AppComponent
import com.example.gifapisimpleapplication.R
import com.example.gifapisimpleapplication.adapters.FeedItemsAdapter
import com.example.gifapisimpleapplication.viewmodels.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment(val isFavorite: Boolean) : BaseFragment() {

    companion object {
        private const val ARG_POSITION = "position"

        fun getInstance(
            position: Int,
            isFavorite: Boolean
        ) = FeedFragment(isFavorite).apply {
            arguments = bundleOf(
                ARG_POSITION to position
            )
        }
    }

    override val viewModel: FeedViewModel by viewModels { AppComponent.viewModelFactory }

    private val feedItemsAdapter: FeedItemsAdapter by lazy {
        FeedItemsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_feed, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list_gifs.apply {
            layoutManager = LinearLayoutManager(this.context)
            this.adapter = feedItemsAdapter
        }

        viewModel.init(isFavorite)
        viewModel.data.observe(viewLifecycleOwner, Observer { feedItemsAdapter.submitList(it) })
    }
}