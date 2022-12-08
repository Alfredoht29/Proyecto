package com.example.luxuryauth2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var etUsuario: EditText
    private lateinit var etPassword: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val registrobtn: Button = findViewById(R.id.registrobtn)
        val ingresarbtn:Button = findViewById(R.id.ingresarbtn)
        etUsuario=findViewById(R.id.etUsuarioMain)
        etPassword=findViewById(R.id.etPasswordMain)
        registrobtn.setOnClickListener{
            registrar()
        }
        ingresarbtn.setOnClickListener {
            login()
        }
    }
    private fun registrar(){
        val intent: Intent = Intent(this,Registro::class.java)
        startActivity(intent)
    }
    private fun login(){
        val usuario:String = etUsuario.text.toString()
        val password:String = etPassword.text.toString()
        val objeto = mutableMapOf<String,Any?>()
        objeto["nickname"]=usuario
        objeto["password"]=password
    }

}