package com.rinoarias.listviewcardviewsitemjsondata

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rinoarias.listviewcardviewsitemjsondata.databinding.ItemUsuarioBinding

class UsuariosAdapter: RecyclerView.Adapter<UsuariosAdapter.MyViewHolder>() {
    var listaUsuarios : MutableList<Usuario> = ArrayList()
    lateinit var context: Context

    class MyViewHolder(val binding: ItemUsuarioBinding) : RecyclerView.ViewHolder(binding.root)

    fun UsuariosAdapter(listUsers: MutableList<Usuario>, context: Context){
        this.listaUsuarios = listUsers
        this.context = context
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemUsuario = listaUsuarios.get(position)
        holder.binding.txtNombres.text = itemUsuario.nombres
        holder.binding.txtEmail.text = itemUsuario.email
        holder.binding.txtWebSite.text = itemUsuario.urlavatar
        //Cargar imagen con Glide
        Glide.with(context).load(itemUsuario.urlavatar).into(holder.binding.imgUser)

        holder.binding.infoUsuario.setOnClickListener {
            Toast.makeText(context, "Usuario Seleccionado: " + itemUsuario.nombres.toString(), Toast.LENGTH_SHORT).show()

            val act = Intent(context.applicationContext, UsuarioActivity::class.java)
            act.putExtra("usuario", listaUsuarios[position])
            act.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.getApplicationContext().startActivity(act)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemUsuarioBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listaUsuarios.size
    }
}