package com.example.gifapisimpleapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gifapisimpleapplication.R
import com.example.gifapisimpleapplication.entities.GifInfo
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_feed.view.*

class FeedItemsAdapter : PagedListAdapter<GifInfo, FeedItemsAdapter.FeedItemViewHolder>(
    GifInfo.DIFF_CALLBACK
) {

    interface Callback {

        fun onClick(gif: GifInfo)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = FeedItemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_feed, parent, false)
    )

    override fun onBindViewHolder(holder: FeedItemsAdapter.FeedItemViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class FeedItemViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun onBind(item: GifInfo?) {
            containerView.txt_gif_name.text =
                item?.title ?: containerView.context.getString(R.string.id_as_name, item?.id)
            if (item == null) {
                //Maybe load placeholder image
            } else {
                Glide.with(containerView.context).load(item.url).into(containerView.img_gif_preview)
            }
        }
    }
}