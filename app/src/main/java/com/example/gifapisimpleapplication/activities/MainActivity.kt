package com.example.gifapisimpleapplication.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.gifapisimpleapplication.AppComponent
import com.example.gifapisimpleapplication.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            val gifResponse = AppComponent.apiClient.gifService.getSearch("cats", 10)
            Log.d("taco", gifResponse.toString())
            withContext(Dispatchers.Main) {
                Glide.with(this@MainActivity)
                    .load(gifResponse.results[0].media[0].gif.url)
                    .into(image_view)
            }
        }
    }
}