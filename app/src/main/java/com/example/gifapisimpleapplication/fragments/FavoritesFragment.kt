package com.example.gifapisimpleapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gifapisimpleapplication.AppComponent
import com.example.gifapisimpleapplication.R
import com.example.gifapisimpleapplication.adapters.FeedItemsAdapter
import com.example.gifapisimpleapplication.viewmodels.BaseViewModel
import com.example.gifapisimpleapplication.viewmodels.FavoritesViewModel
import kotlinx.android.synthetic.main.fragment_feed.*

class FavoritesFragment : BaseFragment() {

    companion object {

        fun getInstance() = FavoritesFragment()
    }

    override val viewModel: FavoritesViewModel by viewModels { AppComponent.viewModelFactory }

    private val feedItemsAdapter: FeedItemsAdapter by lazy {
        FeedItemsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_favorites, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list_gifs.apply {
            layoutManager = LinearLayoutManager(this.context)
            this.adapter = feedItemsAdapter
        }

        //viewModel.data.observe(viewLifecycleOwner, Observer { feedItemsAdapter.submitList(it) })
    }

//    override fun onClick(v: View?) {
//        viewModel.onQueryChanged(txt_query.text.toString())
//    }

}