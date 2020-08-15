package com.example.gifapisimpleapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gifapisimpleapplication.R
import com.example.gifapisimpleapplication.entities.GifInfo
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_feed.view.*


class FeedItemsAdapter(val callback : Callback) :RecyclerView.Adapter<FeedItemsAdapter.FeedItemViewHolder>() {

    private val gifList : MutableList<GifInfo> = mutableListOf()

    fun setGifs(gifs: List<GifInfo>) {
        gifList.clear()
        gifList.addAll(gifs)
        notifyDataSetChanged()
    }

    interface Callback {

        fun onClick(gif : GifInfo)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FeedItemsAdapter.FeedItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return FeedItemViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_feed
    }

    override fun getItemCount(): Int = gifList.size

    override fun onBindViewHolder(holder: FeedItemsAdapter.FeedItemViewHolder, position: Int) {
        holder.onBind(gifList[position])
    }

    inner class FeedItemViewHolder (override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun onBind(item : GifInfo) {
            containerView.txt_gif_name.text = item.title
            if (item.title.isNullOrBlank()) containerView.txt_gif_name.text = containerView.context.getString(R.string.id_as_name, item.id)
            Glide.with(containerView.context).load(item.url).into(containerView.img_gif_preview)
        }
    }

}