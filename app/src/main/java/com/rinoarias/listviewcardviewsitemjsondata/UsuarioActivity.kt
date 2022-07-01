package com.rinoarias.listviewcardviewsitemjsondata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.rinoarias.listviewcardviewsitemjsondata.databinding.ActivityUsuarioBinding
import java.io.Serializable

class UsuarioActivity : AppCompatActivity() {
    lateinit var binding: ActivityUsuarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val datosUsuario = intent.getSerializableExtra("usuario") as Usuario

        binding.txtNombres.text = datosUsuario.nombres
        binding.txtEmail.text = datosUsuario.email
        binding.txtWebSite.text = datosUsuario.urlavatar
        //Cargar imagen con Glide
        Glide.with(binding.root).load(datosUsuario.urlavatar).into(binding.imgUser)

    }
}