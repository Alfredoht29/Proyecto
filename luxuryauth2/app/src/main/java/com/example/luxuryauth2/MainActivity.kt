package com.example.luxuryauth2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var etUsuario: EditText
    private lateinit var etPassword: EditText
    private val url_token="http://192.168.0.17/Proyecto/Login.php"
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
        objeto["usuario"]=usuario
        objeto["password"]=password
        val post: JSONObject = JSONObject(objeto)
        getToken(post)
    }
    private fun getToken(post: JSONObject){
        val queue = Volley.newRequestQueue(this)
        var token=""
        var rol:Int
        val request : JsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,
            url_token,
            post,
            { response ->
                if(response.getBoolean("exito")){
                    Toast.makeText(applicationContext,"Login Exitoso", Toast.LENGTH_SHORT).show()
                    token=response.getString("token")
                    rol=response.getInt("rol")
                    if(rol==1){
                        val intent: Intent = Intent(this,FeedUser::class.java)
                        intent.putExtra("token",token)
                        startActivity(intent)
                    }
                    if(rol==2){
                        val intent: Intent = Intent(this,Feed::class.java)
                        intent.putExtra("token",token)
                        startActivity(intent)
                    }
                }
            },
            {
                    errorResponse ->
                Toast.makeText(applicationContext,"error de conexion", Toast.LENGTH_SHORT).show()
            }
        )
        queue.add(request)
    }

}