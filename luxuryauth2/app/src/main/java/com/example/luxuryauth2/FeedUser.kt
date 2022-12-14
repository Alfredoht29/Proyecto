package com.example.luxuryauth2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FeedUser : AppCompatActivity() {
    private var id=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_user)
        val fbAgregar: FloatingActionButton = findViewById(R.id.fbAgregar)
        id=intent.getIntExtra("id",0)
        fbAgregar.setOnClickListener{
            agregar()
        }
    }
    private fun agregar(){
        val intent: Intent = Intent(this,Agregar::class.java)
        intent.putExtra("id",id)
        startActivity(intent)
    }
}