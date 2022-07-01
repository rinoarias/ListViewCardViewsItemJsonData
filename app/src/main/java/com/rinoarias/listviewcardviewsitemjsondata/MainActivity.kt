package com.rinoarias.listviewcardviewsitemjsondata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.rinoarias.listviewcardviewsitemjsondata.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val UsuariosAdapter : UsuariosAdapter = UsuariosAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cargar_lista_usuarios()

    }

    fun cargar_lista_usuarios() {
        val cola = Volley.newRequestQueue(this)
        val listaUsuarios : MutableList<Usuario> = ArrayList()
        val url1 = "https://reqres.in/api/users"
        val url2 = "https://reqres.in/api/users?page=2"

        obtenerDatosAPI(url1, listaUsuarios, cola)
        obtenerDatosAPI(url2, listaUsuarios, cola)

        binding.rvUsuarios.setHasFixedSize(true)
        binding.rvUsuarios.layoutManager = LinearLayoutManager(this)
        UsuariosAdapter.UsuariosAdapter(listaUsuarios, this)
        binding.rvUsuarios.adapter = UsuariosAdapter
        binding.lblTitulo.text = "LISTA DE USUARIOS"
    }

    fun obtenerDatosAPI(url: String, items: MutableList<Usuario>, queue : RequestQueue) {
        val solicitud = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                try {
                    val array_datos = response.getJSONArray("data")
                    //val items : MutableList<Usuario> = ArrayList()

                    for (i in 0 until array_datos.length()){
                        var item = array_datos.getJSONObject(i)
                        items.add(Usuario(item))
                    }
                    Toast.makeText(this, "Descarga Exitosa", Toast.LENGTH_LONG).show()
                } catch (e: Exception) {
                    Toast.makeText(this, "Error al obtener los datos: $e", Toast.LENGTH_LONG).show()
                }
            }, {
                Toast.makeText(this, "Error al obtener los datos: $it", Toast.LENGTH_LONG).show()
            })
        queue.add(solicitud)
    }
}