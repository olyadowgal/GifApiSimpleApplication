package com.example.gifapisimpleapplication.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.gifapisimpleapplication.R
import com.example.gifapisimpleapplication.entities.GifInfo
import kotlinx.android.synthetic.main.activity_full_screen.*
import kotlinx.android.synthetic.main.item_feed.view.*

class FullScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen)
        val item = intent.getParcelableExtra<GifInfo>("gif")
        Glide.with(this).load(item.url)
            .placeholder(R.drawable.ic_placeholder)
            .into(img_full_screen)
    }
}