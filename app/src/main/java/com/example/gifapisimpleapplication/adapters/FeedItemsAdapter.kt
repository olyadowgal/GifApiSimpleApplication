package com.example.gifapisimpleapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gifapisimpleapplication.R
import com.example.gifapisimpleapplication.cache.GifsCacheManager
import com.example.gifapisimpleapplication.entities.GifInfo
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_feed.view.*

class FeedItemsAdapter(val callback: Callback, val gifsCacheManager: GifsCacheManager) :
    PagedListAdapter<GifInfo, FeedItemsAdapter.FeedItemViewHolder>(
        GifInfo.DIFF_CALLBACK
    ) {

    interface Callback {

        fun onGifClick(gif: GifInfo)
        fun onAddToFavoritesClick(gif: GifInfo)
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
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer, View.OnClickListener,
        CompoundButton.OnCheckedChangeListener {

        init {
            containerView.img_gif_preview.setOnClickListener(this)
        }

        fun onBind(item: GifInfo?) {
            if (item == null) {
                //Maybe load placeholder image
            } else {
                val file = gifsCacheManager.getImageById(item.id)
                if (file != null) {
                    Glide.with(containerView.context)
                        .load(file)
                        .placeholder(R.drawable.ic_placeholder)
                        .into(containerView.img_gif_preview)
                } else {
                    Glide.with(containerView.context)
                        .load(item.url)
                        .placeholder(R.drawable.ic_placeholder)
                        .into(containerView.img_gif_preview)
                }
                if (item.title.isBlank()) {
                    containerView.txt_gif_name.text =
                        containerView.context.getString(R.string.id_as_name, item.id)
                } else {
                    containerView.txt_gif_name.text = item.title
                }

                containerView.btn_add_to_favorites.setOnCheckedChangeListener(null)
                containerView.btn_add_to_favorites.isChecked = item.isFavorite
                containerView.btn_add_to_favorites.setOnCheckedChangeListener(this)
            }
        }

        override fun onClick(view: View?) {
            getItem(adapterPosition)?.let {
                callback.onGifClick(gif = it)
            }
        }

        override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
            getItem(adapterPosition)
                ?.let {
                    it.isFavorite = isChecked
                    callback.onAddToFavoritesClick(gif = it)
                }
        }
    }
}