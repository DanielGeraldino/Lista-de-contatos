package com.example.listacontato.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listacontato.R
import com.example.listacontato.model.Contato
import kotlinx.android.synthetic.main.model_recycler_contato.view.*

class ContatoAdapter(private val listaContato: List<Contato>) : RecyclerView.Adapter<ContatoAdapter.MyViewHolder>() {

    class MyViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.model_recycler_contato, parent, false)){

        private var nome: TextView = itemView.tNome
        private var numero: TextView = itemView.tNumero

        fun preencheDadoContao(contato: Contato){
            nome.text = contato.nome
            numero.text = contato.numero
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return listaContato.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val contato: Contato = listaContato[position]
        holder.preencheDadoContao(contato)
    }

}