package com.example.luxuryauth2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class Feed : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        configFeed()
    }
    private fun configFeed(){
        val intent=intent
        var token = intent.getStringExtra("token")
        if (token != null) {
            Log.e("FeedActivity",token)
        }
    }
}