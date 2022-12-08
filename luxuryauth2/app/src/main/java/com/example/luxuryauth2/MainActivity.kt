package com.example.luxuryauth2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val registrobtn: Button = findViewById(R.id.registrobtn)
        registrobtn.setOnClickListener{
            registrar()
        }
    }
    private fun registrar(){
        val intent: Intent = Intent(this,Registro::class.java)
        startActivity(intent)
    }

}