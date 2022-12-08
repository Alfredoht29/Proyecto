package com.example.luxuryauth2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class Registro : AppCompatActivity() {
    private lateinit var registrarbtn: Button
    private lateinit var etUsuario: EditText
    private lateinit var etPassword: EditText
    private lateinit var rbUsuario: RadioButton
    private lateinit var rbAuditor: RadioButton
    private val url_registro ="http://192.168.0.17/Proyecto/Registrar.php"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        registrarbtn = findViewById(R.id.registrarbtn)
        etUsuario = findViewById(R.id.etUsuario)
        etPassword= findViewById(R.id.etPassword)
        rbUsuario= findViewById(R.id.rbUsuario)
        rbAuditor = findViewById(R.id.rbAuditor)
        registrarbtn.setOnClickListener {
            registrar()
        }
    }
    private fun registrar(){
        val usuario:String = etUsuario.text.toString()
        val password:String = etPassword.text.toString()
        var rol:Int = 0
        if(rbUsuario.isChecked){
            rol=1
        }
        if (rbAuditor.isChecked){
            rol=2
        }
        val objeto = mutableMapOf<String,Any?>()
        objeto["nickname"]=usuario
        objeto["password"]=password
        objeto["rol"]=rol.toString()

        if(rol!=0){
            val post: JSONObject = JSONObject(objeto)
            postRegistro(post)
        }else{
            Toast.makeText(applicationContext,"escoge un rol", Toast.LENGTH_SHORT).show()
        }
    }
    private fun postRegistro(post: JSONObject){
        val queue = Volley.newRequestQueue(this)

        val request : JsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,
            url_registro,
            post,
            {
                    response ->
                if(response.getBoolean("exito")){
                    Toast.makeText(applicationContext,"Registro Exitoso", Toast.LENGTH_SHORT).show()
                    finish()
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