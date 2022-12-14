package com.example.luxuryauth2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray
import org.json.JSONObject

class Feed : AppCompatActivity() {
    private val url_sesion="http://192.168.0.17/Proyecto/Feed.php"
    private val url_listar="http://192.168.0.17/Proyecto/UrlListar.php"
    private var usuario=""
    private var rol=""
    private var id=0
    private lateinit var adapter: TicketAdapter
    private lateinit var etUsuario:TextView
    private lateinit var etRol:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        etUsuario = findViewById(R.id.etUsuarioFeed)
        etRol = findViewById(R.id.etRol)
        val rvFeed: RecyclerView = findViewById(R.id.rvFeed)
        adapter=TicketAdapter()
        rvFeed.adapter = adapter
        rvFeed.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        configFeed()

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
        leerLista()
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
                    id=response.getInt("id")
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

    private fun leerLista(){
        val queue : RequestQueue = Volley.newRequestQueue(this)

        val request : JsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url_listar,
            null,
            {
                    response->
                if(response.getBoolean("exito")){
                    llenarLista( response.getJSONArray("lista") )
                }
            },
            {
                    errorListener->
                Toast.makeText(applicationContext,"Error de conexion", Toast.LENGTH_SHORT).show()
            }
        )

        queue.add(request)
    }
    private fun llenarLista(lista: JSONArray) {
        adapter.limpiar()

        for (i in 0..lista.length() - 1) {
            val v = lista[i] as JSONObject

            var ticket = Ticket()

            ticket.id = v.getInt("id")
            ticket.id_Usuario = v.getInt("id_usuario")
            ticket.id_auditor = v.getInt("id_auditor")
            ticket.modelo = v.getString("modelo")
            ticket.marca = v.getString("marca")
            ticket.imagen = v.getString("imagen")
            adapter.guardar(ticket)
        }
    }
}