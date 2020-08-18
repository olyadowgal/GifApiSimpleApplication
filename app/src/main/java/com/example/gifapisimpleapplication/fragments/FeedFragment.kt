package com.example.gifapisimpleapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.gifapisimpleapplication.AppComponent
import com.example.gifapisimpleapplication.R
import com.example.gifapisimpleapplication.adapters.FeedItemsAdapter
import com.example.gifapisimpleapplication.viewmodels.FeedViewModel
import com.google.android.material.behavior.SwipeDismissBehavior
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment() : BaseFragment(), View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    companion object {

        fun getInstance() = FeedFragment()
    }

    override val viewModel: FeedViewModel by viewModels { AppComponent.viewModelFactory }

    private val feedItemsAdapter: FeedItemsAdapter by lazy {
        FeedItemsAdapter(viewModel, AppComponent.gifCacheManager)
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

        btn_search.setOnClickListener(this)
        swipe_refresh.setOnRefreshListener(this)
        viewModel.data.observe(viewLifecycleOwner, Observer { feedItemsAdapter.submitList(it) })
        viewModel.showSpinner.observe(viewLifecycleOwner, Observer { swipe_refresh.isRefreshing = it == true })
    }

    override fun onClick(v: View?) {
        viewModel.onQueryChanged(txt_query.text.toString())
    }

    override fun onRefresh() {
        viewModel.onRefresh()
    }
}