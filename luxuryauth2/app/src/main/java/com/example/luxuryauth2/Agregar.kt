package com.example.luxuryauth2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.squareup.picasso.Picasso
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class Agregar : AppCompatActivity() {
    private lateinit var etModelo:EditText
    private lateinit var etMarca: EditText
    private lateinit var storage: FirebaseStorage
    private var usuario=0
    private var urlImagen=""
    private val urlTicket="http://192.168.0.17/Proyecto/Ticket.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar)
        val btnSubirImagen: Button = findViewById(R.id.btnSubirImagen)
        val btnEnviar:Button = findViewById(R.id.btnEnviarAgregar)
        etModelo=findViewById(R.id.etModeloAgregar)
        etMarca=findViewById(R.id.etMarcaAgregar)
        storage = Firebase.storage
        usuario=intent.getIntExtra("id",0)
        btnSubirImagen.setOnClickListener{
            agregaimagen()
        }
        btnEnviar.setOnClickListener{
            crearticket()
        }
    }
    private fun crearticket() {
        val modelo: String = etModelo.text.toString()
        val marca: String = etMarca.text.toString()
        val objeto = mutableMapOf<String, Any?>()
        objeto["usuario"] = usuario
        objeto["modelo"] = modelo
        objeto["marca"] = marca
        objeto["imagen"] = urlImagen
        val post: JSONObject = JSONObject(objeto)
        postTicket(post)
    }
    private fun postTicket(post: JSONObject){
        val queue = Volley.newRequestQueue(this)
        var token=""
        val request : JsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,
            urlTicket,
            post,
            { response ->
                if(response.getBoolean("exito")){
                    Toast.makeText(applicationContext,"Ticket pendiente a autorizar", Toast.LENGTH_SHORT).show()
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
    private fun subir(uri: Uri?){
        if(uri!=null){
            val nombreArchivo=generarNombreArchivo()
            Log.e("AgregarActivity",nombreArchivo)
            val storageRef = storage.getReference("images/${nombreArchivo}")
            val carga = storageRef.putFile(uri)
            carga.addOnFailureListener{error->
                Log.e("VideoGameActivity",error.toString())
            }.addOnSuccessListener {
                    task ->
                storageRef.downloadUrl.addOnSuccessListener {it
                    mostrar(it.toString())
                    urlImagen=it.toString()
                }
            }
        }
    }
    private fun generarNombreArchivo():String{
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("yyyy-MM-dd_HH:mm:ss")
        val current = formatter.format(time)
        return "img_$current"
    }

    private fun agregaimagen() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        startActivityForResult(intent,1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK){
            if(requestCode == 1){
                if(data != null) {
                    val uri: Uri? = data.data
                    subir(uri)
                }
            }
        }
    }
    private fun mostrar(url:String){
        if(url.isNotEmpty()){
            val ivImagen: ImageView = findViewById(R.id.ivImagenAgregar)
            Picasso.get().load(url).into(ivImagen)
        }
    }
}