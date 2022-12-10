package com.example.luxuryauth2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONObject

class Feed : AppCompatActivity() {
    private val url_sesion="http://192.168.0.17/Proyecto/Feed.php"
    private var usuario=""
    private var rol=""
    private lateinit var etUsuario:TextView
    private lateinit var etRol:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        etUsuario=findViewById(R.id.etUsuarioFeed)
        etRol=findViewById(R.id.etRol)
        val fbAgregar:FloatingActionButton=findViewById(R.id.fbAgregar)
        configFeed()
        fbAgregar.setOnClickListener{
            agregar()
        }
    }
    private fun configFeed(){
        val intent=intent
        var token=intent.getStringExtra("token")
        val objeto = mutableMapOf<String,Any?>()
        objeto["token"]=token
        val post: JSONObject = JSONObject(objeto)
        getUsuario(post)
        if (token != null) {
            Log.e("FeedActicity",token)
        }
    }
    private fun getUsuario(post:JSONObject){
        var rolint=0
        val queue = Volley.newRequestQueue(this)
        val request : JsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,
            url_sesion,
            post,
            { response ->
                if(response.getBoolean("exito")){
                    usuario=response.getString("usuario")
                    rolint=response.getInt("rol")
                    if(rolint==1){
                        rol="Usuario"
                    }
                    if(rolint==2){
                        rol="Auditor"
                    }
                    etUsuario.setText("Usuario:"+usuario)
                    etRol.setText(rol)
                }
            },
            {
                    errorResponse ->
                Toast.makeText(applicationContext,"error de conexion", Toast.LENGTH_SHORT).show()
            }
        )
        queue.add(request)
    }
    private fun agregar(){
        val intent: Intent = Intent(this,Agregar::class.java)
        startActivity(intent)
    }
}